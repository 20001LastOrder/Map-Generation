using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grid : MonoBehaviour
{
    public enum GridType
    {
        Mountain,
        Valley,
        Lake
    };

    public List<List<Grid>> m_subGrid = new List<List<Grid>>();
    public Grid m_parentGrid;
    public Grid m_left;
    public Grid m_right;
    public Grid m_top;
    public Grid m_bottom;

    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
