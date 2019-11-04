using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Map
{
    public List<Grid> grids { get; set; }

    public Map()
    {
        grids = new List<Grid>();
    }
}
