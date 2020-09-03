using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
[Serializable]
public class ConnectionData {
	public int inNodeIndex;
	public int outNodeIndex;
	public ConnectionType type;
}

[Serializable]
public class NodeData {
	public Rect rect;
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
}

public class GraphEditorData : ScriptableObject
{
	public List<NodeData> nodeData;
	public List<ConnectionData> connectionData;

	public void Initialize() {
		nodeData = new List<NodeData>();
		connectionData = new List<ConnectionData>();
	}

	public void SaveData(GraphEditor editor) {
		Initialize();
		SaveNodes(editor.getNodes());
		SaveConnections(editor.getNodes(), editor.GetConnections());
	}

	public void SaveConnections(List<Node> nodes, List<Connection> connections) {
		foreach(var connection in connections) {
			var data = new ConnectionData {
				inNodeIndex = nodes.IndexOf(connection.inPoint.node),
				outNodeIndex = nodes.IndexOf(connection.outPoint.node),
				type = connection.type
			};
			connectionData.Add(data);
		}
	}

	private void SaveNodes(List<Node> nodes) {
		foreach (var node in nodes) {
			var data = new NodeData {
				rect = new Rect(node.rect.x, node.rect.y, node.rect.width, node.rect.height),
				title = node.title,
				scale = node.scale,
				noiseHeight = node.noiseHeight,
				octaves = node.octaves,
				persistence = node.persistence,
				lacunarity = node.lacunarity,
				meshHeightMultiplier = node.meshHeightMultiplier,
				meshHeightCurve = new AnimationCurve(node.meshHeightCurve.keys),
				heightRemap = new AnimationCurve(node.heightRemap.keys),
				generationRange = new GenerationRange {
					max = node.generationRange.max,
					min = node.generationRange.min
				},
				generationSize = new GenerationSize {
					max = node.generationSize.max,
					min = node.generationSize.min
				}
			};
			nodeData.Add(data);
		}
	}
}
