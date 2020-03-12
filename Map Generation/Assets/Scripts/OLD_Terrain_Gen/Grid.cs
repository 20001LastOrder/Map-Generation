using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;

public class Grid : MonoBehaviour
{
    public enum GridType
    {
        Mountain,
        Valley,
        Lake
    };

    public GeneratedClasses.Grid gridData;

    public List<List<Grid>> m_subGrid = new List<List<Grid>>();
    public Grid m_parentGrid;
    public Grid m_left;
    public Grid m_right;
    public Grid m_top;
    public Grid m_bottom;

    void OnDrawGizmos()
    {
        Type myType = gridData.Types[0].GetType();
        Handles.Label(transform.position, myType.ToString());
    }
}
