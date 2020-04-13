using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using GeneratedClasses;


public class RegionInstance 
{
    public RegionInstance parent;
    public List<RegionInstance> children;


    public Region region;

    public Vector2 top_left;
    public int size;

    public RegionInstance(Region _region)
    {
        region = _region;
    }

    public bool Equals(RegionInstance other)
    {
        return other != null && top_left.Equals(other.top_left) && size == other.size;
    }
}
