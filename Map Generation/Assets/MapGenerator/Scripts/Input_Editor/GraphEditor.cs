using UnityEditor;
using UnityEngine;
using System.Collections.Generic;
using System;
using System.IO;
using System.Collections;
using UnityEditor.Callbacks;

[InitializeOnLoadAttribute]
public class GraphEditor : EditorWindow
{
    private List<Node> nodes;
    private List<Connection> connections;
	private GraphEditorData data;
    private GUIStyle nodeStyle;
    private GUIStyle selectedNodeStyle;

    private Node selectedNode;
    private Node selectedInNode;
    private Node selectedOutNode;

	private SerializedObject serializedObject;

    private Connection selectedConnection;

    private Vector2 offset;
    private Vector2 drag;

    private static GraphEditor _instance;
    public static GraphEditor Instance => _instance;

    private bool _hasProgressBarLog = false;
    private string _progressBarInfo;
    private float _progressBarProgress;

	private bool waitingForReload = false;
	private static bool justReload = false;

	private List<TerrainType> types = new List<TerrainType>();
	private string extraConstraintFilePath;

    [MenuItem("Window/Graph Editor")]
    public static void ShowWindow()
    {
        GetWindow<GraphEditor>("Graph Editor");
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    public List<Connection> GetConnections()
    {
        return connections;
    }

	public void WaitForReload() {
		waitingForReload = true;
	}

	static GraphEditor() {
		justReload = true;
	}

    private void OnEnable()
    {
        nodeStyle = new GUIStyle();
        nodeStyle.normal.background = EditorGUIUtility.Load("builtin skins/darkskin/images/node1.png") as Texture2D;
        nodeStyle.border = new RectOffset(12, 12, 12, 12);
        selectedNodeStyle = new GUIStyle();
        selectedNodeStyle.normal.background = EditorGUIUtility.Load("builtin skins/darkskin/images/node1 on.png") as Texture2D;
        selectedNodeStyle.border = new RectOffset(12, 12, 12, 12);
		// Then we apply them to this window
		Load();
		// set instance to be used in other thread 
		_instance = this;
		serializedObject = new SerializedObject(this);
    }

    private void OnDisable()
    {
        // Save data
        Save();

        // set instance to null
        _instance = null;

        // unregister the pipeline checking method
        EditorApplication.update -= CheckPipeLineSecondStage;
        EditorApplication.update -= CheckProgressBarLog;
    }

    private void OnGUI()
    {
        Rect sidebar = new Rect(position.width * 3 / 4, 0, position.width / 4, position.height);
        Rect mainArea = new Rect(0, 0, position.width * 3 / 4, position.height);

        BeginWindows();

        // Main Window
        GUILayout.BeginArea(mainArea);
        DrawGrid(20, 0.2f, Color.gray);
        DrawGrid(100, 0.4f, Color.gray);
        
        DrawNodes();
        DrawConnections();

        DrawConnectionLine(Event.current);
        GUILayout.EndArea();

        // Side Bar
        GUILayout.BeginArea(sidebar);
        DrawInspector();
        GUILayout.EndArea();

        ProcessNodeEvents(Event.current);
        ProcessEvents(Event.current);

        // Update
        if (GUI.changed) Repaint();
        EndWindows();

        if (GUI.Button(new Rect(0, 0, position.width, position.height), "", GUIStyle.none))
        {
            GUI.FocusControl(null);
        }
    }

	public void Update() {
		if (waitingForReload && justReload) {
			waitingForReload = false;
			StartGeneration();
		}
		justReload = false;
	}

	private void DrawInspector()
    {
        // Draw inspector components for a selected node
        if (selectedNode != null)
        {
			GUIStyle style = new GUIStyle(GUI.skin.label) {
				fontSize = 15
			};
			GUILayout.Label("Title:", style);
            selectedNode.title = GUILayout.TextField(selectedNode.title);

            GUILayout.Label("Octaves:", style);
            selectedNode.octaves = EditorGUILayout.IntField(selectedNode.octaves);

            GUILayout.Label("Noise Scale:", style);
            selectedNode.scale = EditorGUILayout.FloatField(selectedNode.scale);

			//GUILayout.Label("Height:");
			//selectedNode.noiseHeight = EditorGUILayout.FloatField(selectedNode.noiseHeight);

			GUILayout.Label("Persistence:", style);
            selectedNode.persistence = EditorGUILayout.Slider(selectedNode.persistence, 0, 1.0f);

            GUILayout.Label("Lacunarity:", style);
            selectedNode.lacunarity = EditorGUILayout.Slider(selectedNode.lacunarity, 1f, 10);

            GUILayout.Label("Mesh Height Multiplier:", style);
            selectedNode.meshHeightMultiplier = EditorGUILayout.FloatField(selectedNode.meshHeightMultiplier);

            GUILayout.Label("Mesh Height Curve", style);
            selectedNode.meshHeightCurve = EditorGUILayout.CurveField(selectedNode.meshHeightCurve);

            GUILayout.Label("Height Remap", style);
            selectedNode.heightRemap = EditorGUILayout.CurveField(selectedNode.heightRemap);

            GUILayout.Label("Generation Range Minimum", style);
            selectedNode.generationRange.min = EditorGUILayout.IntField(selectedNode.generationRange.min);

            GUILayout.Label("Generation Range Maximum", style);
            selectedNode.generationRange.max = EditorGUILayout.IntField(selectedNode.generationRange.max);

			//GUILayout.Label("Generation Size Maximum");
			//selectedNode.generationSize.max = EditorGUILayout.IntField(selectedNode.generationRange.max);

			//GUILayout.Label("Generation Size Maximum");
			//selectedNode.generationSize.min = EditorGUILayout.IntField(selectedNode.generationRange.max);

			RenderAttributeFields();

            if (GUILayout.Button("Add attribute"))
            {
                selectedNode.AddAttribute();
            }
        }

		// Add generate button
		GUILayout.FlexibleSpace();
		GUILayout.Label("Currently Selected File: " + (extraConstraintFilePath ?? ""));
		GUILayout.BeginHorizontal();
		if (GUILayout.Button("Select Extra Constraints File")) {
			extraConstraintFilePath = EditorUtility.OpenFilePanel("Choose VIATRA Constraint file", Application.dataPath, "vql");
		}

		if (GUILayout.Button("Clear Extra Constraint File")) {
			extraConstraintFilePath = null;
		}
		GUILayout.EndHorizontal();

		if (GUILayout.Button("Save") && EditorUtility.DisplayDialog("Manual Save?",
                "Are you sure you want to manually save?", "Save", "Cancel"))
        {
            Save();
        }
        if (GUILayout.Button("Load"))
        {
			Load();
        }
        if (GUILayout.Button("Generate"))
        {
			StartGeneration();

		}        
    }

	private void StartGeneration() {
		try {
			Pipeline.execute();

			// register the pipeline second stage checking method
			EditorApplication.update += CheckPipeLineSecondStage;
			EditorApplication.update += CheckProgressBarLog;
		} catch (Exception e) {
			// ensure that if any exception has happened, the pipeline can become to the original status
			Pipeline.CurrentStatus = Pipeline.Status.Idle;
			throw new Exception("Exception Happened", e);
		}
	}

    private void CheckPipeLineSecondStage()
    {
        Exception exception = null;
        if (Pipeline.CurrentStatus == Pipeline.Status.Stage1Finished)
        {
            Debug.Log("Stage II Started");
            try
            {
                Pipeline.execute();
            } 
            catch (Exception e)
            {
                // ensure that if any exception has happened, the pipeline can become to the original status
                exception = e;
                Pipeline.CurrentStatus = Pipeline.Status.Idle;
            }
            finally
            {
                // always unregister the second stage checking method
                EditorApplication.update -= CheckPipeLineSecondStage;
                EditorApplication.update -= CheckProgressBarLog;
                ClearProgressBar();
				waitingForReload = false;
				if (exception != null)
                {
					Debug.Log(exception.StackTrace);
                    throw new Exception("Exception Happened", exception);
                }
            }
        }
    }

    private void CheckProgressBarLog()
    {
        if (_hasProgressBarLog)
        {
            ShowProgressBar(_progressBarInfo, _progressBarProgress);
            _hasProgressBarLog = false;
        }
    }

    private void DrawGrid(float gridSpacing, float gridOpacity, Color gridColor)
    {
        int widthDivs = Mathf.CeilToInt(position.width / gridSpacing);
        int heightDivs = Mathf.CeilToInt(position.height / gridSpacing);

        Handles.color = new Color(gridColor.r, gridColor.g, gridColor.b, gridOpacity);

        offset += drag * 0.5f;
        Vector3 newOffset = new Vector3(offset.x % gridSpacing, offset.y % gridSpacing, 0);

        for (int i = 0; i < widthDivs; i++)
        {
            Handles.DrawLine(new Vector3(gridSpacing * i, -gridSpacing, 0) + newOffset, new Vector3(gridSpacing * i, position.height, 0f) + newOffset);
        }

        for (int j = 0; j < heightDivs; j++)
        {
            Handles.DrawLine(new Vector3(-gridSpacing, gridSpacing * j, 0) + newOffset, new Vector3(position.width, gridSpacing * j, 0f) + newOffset);
        }

        Handles.color = Color.white;
    }

    private void RenderAttributeFields()
    {
        if (selectedNode != null)
        {
            GUILayout.Label("Attributes:");
            for (int i = 0; i < selectedNode.GetNumAttributes(); i++)
            {
                GUILayout.BeginHorizontal();

                string key, value;
                (key, value) = selectedNode.GetAttributeAt(i);

                // Select attribute type
                int selected = Array.IndexOf(Node.types, key) == -1 ? 0 : Array.IndexOf(Node.types, key);
                selected = EditorGUILayout.Popup(selected, Node.types, GUILayout.MaxWidth(75.0f));
                key = Node.types[selected];

                // Select attribute value
                value = GUILayout.TextField(value);
                if (GUILayout.Button("x", GUILayout.Width(15), GUILayout.Height(15)))
                {
                    selectedNode.RemoveAttributeAt(i);
                    break;
                }
                GUILayout.EndHorizontal();

                selectedNode.SetAttributeAt(key, value, i);
            }
        }
    }

    private void DrawNodes()
    {
        if (nodes != null)
        {
            for (int i = 0; i < nodes.Count; i++)
            {
                nodes[i].Draw();
            }
        }
    }

    private void DrawConnections()
    {
        if (connections != null)
        {
            for (int i = 0; i < connections.Count; i++)
            {
                connections[i].Draw();
            }
        }
    }

    private void ProcessEvents(Event e)
    {
        drag = Vector2.zero;

        switch (e.type)
        {
            case EventType.MouseDown:
                if (e.button == 1)
                {
                    ProcessContextMenu(e.mousePosition);
                }
                break;

            case EventType.MouseDrag:
                if (e.button == 0)
                {
                    OnDrag(e.delta);
                }
                break;
        }
    }

    private void ProcessNodeEvents(Event e)
    {
        if (nodes != null)
        {
            for (int i = nodes.Count - 1; i >= 0; i--)
            {
                bool guiChanged = nodes[i].ProcessEvents(e);

                if (guiChanged)
                {
                    GUI.changed = true;
                }
            }
        }
    }

    private void ProcessContextMenu(Vector2 mousePosition)
    {
        GenericMenu genericMenu = new GenericMenu();
        genericMenu.AddItem(new GUIContent("Add node"), false, () => OnClickAddNode(mousePosition));
        genericMenu.ShowAsContext();
    }

    private void OnClickAddNode(Vector2 mousePosition)
    {
        if (nodes == null)
        {
            nodes = new List<Node>();
        }

        nodes.Add(new Node(mousePosition, nodeStyle, selectedNodeStyle, OnClickRemoveNode, OnClickCreateConnection, OnClickNode));
    }

    private void DrawConnectionLine(Event e)
    {
        if (selectedInNode != null && selectedOutNode == null)
        {
            Handles.DrawLine(selectedInNode.inPoint.rect.center, e.mousePosition);
            GUI.changed = true;
        }

        if (selectedOutNode != null && selectedInNode == null)
        {
            Handles.DrawLine(selectedOutNode.outPoint.rect.center, e.mousePosition);
            GUI.changed = true;
        }
    }

    private void OnClickCreateConnection(Node node)
    {
        selectedInNode = node;
        selectedOutNode = null;
    }

    private void OnClickNode(Node node)
    {
        if (selectedInNode != null)
        {
            selectedOutNode = node;
            CreateConnection();
            ClearConnectionSelection();
        }
    }

    private void OnDrag(Vector2 delta)
    {
        drag = delta;

        if (nodes != null)
        {
            for (int i = 0; i < nodes.Count; i++)
            {
                nodes[i].Drag(delta);
            }
        }

        GUI.changed = true;
    }

    private void OnClickConnection(Connection connection)
    {
        selectedConnection = connection;

        GenericMenu connectionMenu = new GenericMenu();
        
        var values = System.Enum.GetValues(typeof(ConnectionType));

        foreach (ConnectionType type in System.Enum.GetValues(typeof(ConnectionType)))
        {
            connectionMenu.AddItem(new GUIContent(type.ToString()), false, () => connection.SetType(type));
        }
        
        connectionMenu.AddSeparator("");
        connectionMenu.AddItem(new GUIContent("Remove Connection"), false, () => connections.Remove(connection));
        connectionMenu.ShowAsContext();
    }

    private void OnClickRemoveNode(Node node)
    {
        if (connections != null)
        {
            List<Connection> connectionsToRemove = new List<Connection>();

            for (int i = 0; i < connections.Count; i++)
            {
                if (connections[i].inPoint == node.inPoint || connections[i].outPoint == node.outPoint)
                {
                    connectionsToRemove.Add(connections[i]);
                }
            }

            for (int i = 0; i < connectionsToRemove.Count; i++)
            {
                connections.Remove(connectionsToRemove[i]);
            }

            connectionsToRemove = null;
        }

        selectedNode = null;
        nodes.Remove(node);
    }

    private void CreateConnection()
    {
        if (connections == null)
        {
            connections = new List<Connection>();
        }

        connections.Add(new Connection(selectedInNode.inPoint, selectedOutNode.outPoint, OnClickConnection));
    }

    private void ClearConnectionSelection()
    {
        selectedInNode = null;
        selectedOutNode = null;
    }

    public void SetSelectedNode(Node node)
    {
        selectedNode = node;
	}

    public void Save()
    {
		data.SaveData(this);
		EditorUtility.SetDirty(data);
	}

	public void Load() {
		data = AssetDatabase.LoadAssetAtPath<GraphEditorData>(ProjectConfig.RELATIVE_PROJECT_PATH + "/Resources/GraphEditorData.asset");
		if (data == null) {
			data = CreateInstance<GraphEditorData>();
			data.Initialize();
			AssetDatabase.CreateAsset(data, ProjectConfig.RELATIVE_PROJECT_PATH + "/Resources/GraphEditorData.asset");
		}
		LoadNodes(data.nodeData);
		LoadConnections(data.connectionData);
	}

	public void LoadNodes(List<NodeData> datas)
    {
        if (nodes == null)
        {
            nodes = new List<Node>();
        }
        else
        {
            nodes.Clear();
            selectedNode = null;
            selectedInNode = null;
            selectedOutNode = null;
        }

        foreach (var data in datas) {
			Node node = new Node(new Vector2(data.rect.x, data.rect.y), nodeStyle, selectedNodeStyle, OnClickRemoveNode, OnClickCreateConnection, OnClickNode) {
				title = data.title,
				scale = data.scale,
				noiseHeight = data.noiseHeight,
				octaves = data.octaves,
				persistence = data.persistence,
				lacunarity = data.lacunarity,
				meshHeightMultiplier = data.meshHeightMultiplier,
				meshHeightCurve = new AnimationCurve(data.meshHeightCurve.keys),
				heightRemap = new AnimationCurve(data.heightRemap.keys),
				generationRange = new GenerationRange {
					max = data.generationRange.max,
					min = data.generationRange.min
				},
				generationSize = new GenerationSize {
					max = data.generationSize.max,
					min = data.generationSize.min
				}
			};
			nodes.Add(node);
		}
    }

    public void LoadConnections(List<ConnectionData> datas)
    {
        if (connections == null)
        {
            connections = new List<Connection>();
        }
        else
        {
            connections.Clear();
            selectedConnection = null;
        }

		foreach (var data in datas) {
			var connection = new Connection(nodes[data.inNodeIndex].inPoint, nodes[data.outNodeIndex].outPoint, OnClickConnection) {
				type = data.type
			};
			connections.Add(connection);
		}
	}

    public void ShowProgressBarAsync(string info, float progress)
    {
        //flag to update the progress
        _progressBarInfo = info;
        _progressBarProgress = progress;
        _hasProgressBarLog = true;
    }

    public void ShowProgressBar(string info, float progress)
    {
        EditorUtility.DisplayProgressBar("Map Generation Progress", info, progress);
    }

    public void ClearProgressBar()
    {
        EditorUtility.ClearProgressBar();
    }

	public string getExtraConstraintFilePath() {
		return extraConstraintFilePath;
	}
}
