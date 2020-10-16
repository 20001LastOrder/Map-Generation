using System;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

[Serializable]
public class GenerationRange{
    public int min;
    public int max;
}

[Serializable]

public class GenerationSize {
    public float min { get; set; }
    public float max { get; set; }
}
public class Node
{
    // For Display
    public Rect rect;
    public bool isDragged;
    public bool isSelected;

    public ConnectionPoint inPoint;
    public ConnectionPoint outPoint;

    private GUIStyle style;
	private GUIStyle defaultNodeStyle;
	private GUIStyle selectedNodeStyle;

	private Action<Node> OnRemoveNode;
	private Action<Node> OnCreateConnection;
	private Action<Node> OnCompleteConnection;

    public float width = 150;
    public float height = 50;

    private long lastSelected;
    private static int doubleClickThresh = 250;

    // Node Attributes
    public static readonly string[] types = { "int", "double", "string", "bool" };
    public string title;
    public int octaves;
    public float scale;
	public float noiseHeight;
    public float persistence;
    public float lacunarity;
    public float meshHeightMultiplier;
    public AnimationCurve meshHeightCurve;
    public AnimationCurve heightRemap;
    public GenerationRange generationRange;
	public GenerationSize generationSize;


	public bool isComposite;

	// this property is inferred during the generation
	[NonSerialized]
	public bool isRoot;
	private List<(string, string)> attributes;

    public Node(Vector2 position, GUIStyle nodeStyle, GUIStyle selectedStyle, Action<Node> OnClickRemoveNode, Action<Node> OnClickCreateConnection, Action<Node> OnClickCompleteConnection)
    {
        rect = new Rect(position.x, position.y, width, height);
        style = nodeStyle;
        inPoint = new ConnectionPoint(this, ConnectionPointType.In);
        outPoint = new ConnectionPoint(this, ConnectionPointType.Out);
        lastSelected = 0;
        defaultNodeStyle = nodeStyle;
        selectedNodeStyle = selectedStyle;
        OnRemoveNode = OnClickRemoveNode;
        OnCreateConnection = OnClickCreateConnection;
        OnCompleteConnection = OnClickCompleteConnection;

        title = "";
        octaves = 1;
        persistence = 1f;
        scale = 1f;
        lacunarity = 1f;
        meshHeightMultiplier = 1f;
        meshHeightCurve = AnimationCurve.Linear(0, 0, 1, 1);
        heightRemap = AnimationCurve.Linear(0, 0, 1, 1);
        generationRange = new GenerationRange();
        generationSize = new GenerationSize() { max =0.7f, min=0.1f};
		attributes = new List<(string key, string value)>();
		isRoot = false;
    }

    public void Drag(Vector2 delta)
    {
        rect.position += delta;
    }

    public void Draw()
    {
        inPoint.Draw();
        outPoint.Draw();
		GUIStyle boxStyle = new GUIStyle(GUI.skin.box);
		boxStyle.fontSize = 20;
		GUI.Box(rect, title, boxStyle);
    }

    public bool ProcessEvents(Event e)
    {
        switch (e.type)
        {
            case EventType.MouseDown:
                if (e.button == 0)
                {
                    long now = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
                    if (now - lastSelected < doubleClickThresh && rect.Contains(e.mousePosition))
                    {
                        Debug.Log("Double Click");
                    }

                    if (rect.Contains(e.mousePosition))
                    {
                        isDragged = true;
                        GUI.changed = true;

                        // Set selected node only if not already selected
                        if (!isSelected)
                        {
                            EditorWindow.GetWindow<GraphEditor>().SetSelectedNode(this);
                        }

                        isSelected = true;
                        style = selectedNodeStyle;
                        OnClickCompleteConnection();
                    }
                    else
                    {
                        GUI.changed = true;
                        isSelected = false;
                        style = defaultNodeStyle;
                    }
                    lastSelected = now;
                }

                if (e.button == 1 && isSelected && rect.Contains(e.mousePosition))
                {
                    ProcessContextMenu();
                    e.Use();
                }
                break;

            case EventType.MouseUp:
                isDragged = false;
                break;

            case EventType.MouseDrag:
                if (e.button == 0 && isDragged)
                {
                    Drag(e.delta);
                    e.Use();
                    return true;
                }
                break;
        }

        return false;
    }

    private void ProcessContextMenu()
    {
        GenericMenu genericMenu = new GenericMenu();
        genericMenu.AddItem(new GUIContent("Create Connection"), false, OnClickCreateConnection);
        genericMenu.AddItem(new GUIContent("Remove node"), false, OnClickRemoveNode);
        genericMenu.ShowAsContext();
    }

    private void OnClickCreateConnection()
    {
        OnCreateConnection(this);
    }

    private void OnClickCompleteConnection()
    {
        OnCompleteConnection(this);
    }

    private void OnClickRemoveNode()
    {
        OnRemoveNode(this);
    }

    public void AddAttribute()
    {
        attributes.Add(("", ""));
    }

    public void AddAttribute(string key, string value)
    {
        attributes.Add((key, value));
    }

    public void SetAttributeAt(string key, string value, int i)
    {
        attributes[i] = (key, value);
    }

    public List<(string, string)> GetAttributes()
    {
        return attributes;
    }

    public void RemoveAttributeAt(int i)
    {
        if (attributes != null && i < attributes.Count && i >= 0)
        {
            attributes.RemoveAt(i);
        }
    }

    public (string, string) GetAttributeAt(int i)
    {
        if (attributes != null && i < attributes.Count)
        {
            return attributes[i];
        }
        return ("", "");
    }

    public int GetNumAttributes()
    {
        if (attributes != null)
        {
            return attributes.Count;
        }
        return 0;
    }

	public Node CopyTo(Node other) {
		other.rect = new Rect(rect.x, rect.y, rect.width, rect.height);
		other.title = this.title;
		other.scale = this.scale;
		other.noiseHeight = this.noiseHeight;
		other.octaves = this.octaves;
		other.persistence = this.persistence;
		other.lacunarity = this.lacunarity;
		other.meshHeightMultiplier = this.meshHeightMultiplier;
		other.meshHeightCurve = new AnimationCurve(this.meshHeightCurve.keys);
		other.heightRemap = new AnimationCurve(this.heightRemap.keys);
		other.generationRange.max = this.generationRange.max;
		other.generationRange.min = this.generationRange.min;
        other.generationSize.max = this.generationSize.max;
        other.generationSize.min = this.generationSize.min;
		return other;
	}
}
