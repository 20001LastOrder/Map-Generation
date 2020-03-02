using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using GeneratedClasses;

public class RegionParser : PipelineStage
{
    // TODO: make these parameters, its just hard coded for now
    public static int map_size = 1000;

    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing RegionParser-----");

        Region region = (Region)input;

        RegionInstance regionInstance = parseRegion(region, null);
        Debug.Log(regionInstance.children.Count);

        return regionInstance;
    }


    RegionInstance parseRegion(Region region, RegionInstance parent)
    {
        RegionInstance regionInstance = new RegionInstance(region);
        regionInstance.parent = parent;

        Vector2 top_left;
        int size;
        if(parent == null)
        {
            top_left = new Vector2(0.0f, 0.0f);
            size = map_size;
        }
        else {
            size = (int)((float)parent.size * Random.Range(0.1f, 0.6f));
            top_left = new Vector2(Random.Range(parent.top_left.x, parent.top_left.x + parent.size - size),
                Random.Range(parent.top_left.y, parent.top_left.y + parent.size - size));
        }

        regionInstance.top_left = top_left;
        regionInstance.size = size;

        if (region is CompositeRegion)
        {
            CompositeRegion compRegion = (CompositeRegion)region;
            List<RegionInstance> children = new List<RegionInstance>();

            foreach (Region childRegion in compRegion.insides)
            {
                children.Add(parseRegion(childRegion, regionInstance));
            }

            regionInstance.children = children;
        }
        else regionInstance.children = new List<RegionInstance>();

        return regionInstance;
    }
}
