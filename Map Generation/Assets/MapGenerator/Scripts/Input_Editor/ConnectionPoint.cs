using System;
using UnityEngine;

public enum ConnectionPointType { In, Out }
[Serializable]
public class ConnectionPoint
{
    public Rect rect;

    public ConnectionPointType type;

    public Node node;

    public ConnectionPoint(Node node, ConnectionPointType type)
    {
        this.node = node;
        this.type = type;
        rect = new Rect(0, 0, 0f, 0f);
        rect.y = node.rect.y + node.rect.height * 0.5f;
        rect.x = node.rect.x + node.rect.width * 0.5f;
    }

    public void Draw()
    {
        rect.y = node.rect.y + node.rect.height * 0.5f;
        rect.x = node.rect.x + node.rect.width * 0.5f;
    }
}