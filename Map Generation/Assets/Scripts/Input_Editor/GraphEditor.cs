using UnityEditor;
using UnityEngine;
using System.Collections.Generic;
using System.Globalization;
using System;

public class GraphEditor : EditorWindow
{
    private List<Node> nodes;
    private List<Connection> connections;

    private GUIStyle nodeStyle;
    private GUIStyle selectedNodeStyle;

    private Node selectedNode;
    private Node selectedInNode;
    private Node selectedOutNode;

    private Connection selectedConnection;

    private Vector2 offset;
    private Vector2 drag;

    [MenuItem("Window/Graph Editor")]
    public static void ShowWindow()
    {
        GetWindow<GraphEditor>("Graph Editor");
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    private void OnEnable()
    {
        nodeStyle = new GUIStyle();
        nodeStyle.normal.background = EditorGUIUtility.Load("builtin skins/darkskin/images/node1.png") as Texture2D;
        nodeStyle.border = new RectOffset(12, 12, 12, 12);

        selectedNodeStyle = new GUIStyle();
        selectedNodeStyle.normal.background = EditorGUIUtility.Load("builtin skins/darkskin/images/node1 on.png") as Texture2D;
        selectedNodeStyle.border = new RectOffset(12, 12, 12, 12);
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

    }

    private void DrawInspector()
    {
        // Draw inspector components for a selected connection
        if (selectedConnection != null)
        {
            GUILayout.Label(selectedConnection.inPoint.node.title + " -> " + selectedConnection.outPoint.node.title);
            if (selectedConnection.type == ConnectionType.Probability)
            {
                GUILayout.Label("Probability:");
                selectedConnection.probability = EditorGUILayout.Slider(selectedConnection.probability, 0, 1.0f);
            }
        }

        // Draw inspector components for a selected node
        if (selectedNode != null)
        {
            GUILayout.Label("Title:");
            selectedNode.title = GUILayout.TextField(selectedNode.title);

            GUILayout.Label("Weight:");
            selectedNode.weight = EditorGUILayout.Slider(selectedNode.weight, 0, 1.0f);

            RenderAttributeFields();

            if (GUILayout.Button("Add attribute"))
            {
                selectedNode.AddAttribute();
            }
        }

        // Add generate button
        GUILayout.FlexibleSpace();
        if (GUILayout.Button("Generate"))
        {
            Generate.generateEPackage(nodes, connections);
            // Pipeline.execute();
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
            if (selectedNode != null && !selectedNode.isSelected)
            {
                selectedNode = null;
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
}
