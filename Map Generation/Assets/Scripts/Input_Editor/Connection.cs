﻿using System;
using UnityEditor;
using UnityEngine;

public enum ConnectionType {
    Default,
    Insides,
    Close
}

public class Connection
{
    public ConnectionType type;
    public ConnectionPoint inPoint;
    public ConnectionPoint outPoint;
    public Action<Connection> OnClickConnection;
    public float probability;

    public Connection(ConnectionPoint inPoint, ConnectionPoint outPoint, Action<Connection> OnClickConnection)
    {
        this.inPoint = inPoint;
        this.outPoint = outPoint;
        this.OnClickConnection = OnClickConnection;
        this.probability = 0.5f;
        type = ConnectionType.Default;
    }

    public void Draw()
    {
        adjustPoints();

        int direction = inPoint.node.rect.center.y > outPoint.node.rect.center.y ? 1 : -1;

        Handles.DrawBezier(
            inPoint.rect.center,
            outPoint.rect.center,
            inPoint.rect.center + direction * Vector2.down * 50f,
            outPoint.rect.center + direction * Vector2.up * 50f,
            Color.white,
            null,
            2f
        );

        string displayType = type == ConnectionType.Default ? "" : type.ToString();
        Handles.Label((inPoint.rect.center + outPoint.rect.center) * 0.5f, displayType);

        if (Handles.Button((inPoint.rect.center + outPoint.rect.center) * 0.5f, Quaternion.identity, 4, 8, Handles.RectangleHandleCap))
        {
            OnClickConnection(this);
        }
    }

    public void SetType(ConnectionType type)
    {
        this.type = type;
    }

    private void adjustPoints()
    {
        if (inPoint.node.rect.center.y > outPoint.node.rect.center.y)
        {
            inPoint.rect.y = inPoint.node.rect.y;
            outPoint.rect.y = outPoint.node.rect.y + outPoint.node.rect.height;

            Vector3[] arrowLine = new Vector3[3];
            arrowLine[0] = new Vector3(inPoint.rect.x - 6, inPoint.rect.y - 8);
            arrowLine[1] = new Vector3(inPoint.rect.x, inPoint.rect.y);
            arrowLine[2] = new Vector3(inPoint.rect.x + 6, inPoint.rect.y - 8);

            Handles.DrawAAPolyLine(2f, arrowLine);
        }
        else
        {
            inPoint.rect.y = inPoint.node.rect.y + inPoint.node.rect.height;
            outPoint.rect.y = outPoint.node.rect.y;

            Vector3[] arrowLine = new Vector3[3];
            arrowLine[0] = new Vector3(inPoint.rect.x - 6, inPoint.rect.y + 8);
            arrowLine[1] = new Vector3(inPoint.rect.x, inPoint.rect.y);
            arrowLine[2] = new Vector3(inPoint.rect.x + 6, inPoint.rect.y + 8);

            Handles.DrawAAPolyLine(2f, arrowLine);
        }
    }

    public void SetProbability(int probability)
    {
        this.probability = probability;
    }
}
