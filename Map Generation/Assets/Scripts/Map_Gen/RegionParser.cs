using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using GeneratedClasses;

public class RegionParser : PipelineStage
{
    // TODO: make these parameters, its just hard coded for now
    static float map_size = 1000.0f;

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
        Vector2 bottom_right;
        float size;
        if(parent == null)
        {
            top_left = new Vector2(-map_size, map_size);
            bottom_right = new Vector2(map_size, -map_size);
            size = map_size * 2.0f;
        }
        else {
            size = parent.size * Random.Range(0.1f, 0.6f);
            top_left = new Vector2(Random.Range(parent.top_left.x, parent.bottom_right.x - size),
                Random.Range(parent.top_left.y, parent.bottom_right.y - size));
            bottom_right = new Vector2(top_left.x + size, top_left.y - size); 
        }

        regionInstance.top_left = top_left;
        regionInstance.bottom_right = bottom_right;
        regionInstance.size = size;

        if(region is CompositeRegion)
        {
            CompositeRegion compRegion = (CompositeRegion) region;
            List<RegionInstance> children = new List<RegionInstance>();
            
            foreach(Region childRegion in compRegion.insides)
            {
                children.Add(parseRegion(childRegion, regionInstance));
            }

            regionInstance.children = children;
        }

        return regionInstance;
    }
}
