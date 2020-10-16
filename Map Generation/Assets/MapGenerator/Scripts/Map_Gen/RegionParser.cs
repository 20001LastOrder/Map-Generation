using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using GeneratedClasses;

public class RegCollisionInfo
{
    public RegCollisionInfo()
    {
        top_left = new Vector2();
        size = 0;
    }

    public RegCollisionInfo(Vector2 _top_left,
        int _size, RegionInstance _parent)
    {
        top_left = _top_left;
        size = _size;
        parent = _parent;
    }

    public Vector2 top_left;
    public int size;
    public RegionInstance parent;
}
public class RegionParser : PipelineStage
{
    // TODO: make these parameters, its just hard coded for now
    public static int map_size = 100;
    List<Node> nodes;
    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing RegionParser-----");

        Region region = (Region)input;


        List<RegCollisionInfo> curRegs = new List<RegCollisionInfo>();
        nodes = GraphEditor.Instance.getNodes();
        RegionInstance regionInstance = parseRegion(region, null, curRegs);

        return regionInstance;
    }

    public bool checkRegionCollision(Vector2 top_left, int size, RegionInstance parent, 
        List<RegCollisionInfo> curRegs)
    {
        Vector2 center = new Vector2(top_left.x + (size / 2.0f),
                                            top_left.y + (size / 2.0f));
        foreach(RegCollisionInfo cur in curRegs)
        {
            if(!parent.Equals(cur.parent)) continue;

            Vector2 cur_center = new Vector2(cur.top_left.x + (cur.size / 2.0f),
                                            cur.top_left.y + (cur.size / 2.0f));
            if(Mathf.Abs(center.x - cur_center.x) * 2 < size + cur.size &&
            Mathf.Abs(center.y - cur_center.y) * 2 < size + cur.size) return true;
        }

        return false;
    }

    public string GetInfo()
    {
        return "Preparing Visualization...";
    }

    RegionInstance parseRegion(Region region, RegionInstance parent,
        List<RegCollisionInfo> curRegs)
    {
        RegionInstance regionInstance = new RegionInstance(region);
        regionInstance.parent = parent;
        Node regionInput = nodes.Find(x => x.title.Equals(getRegionTypeString(region)));
        Vector2 top_left;
        int size;
        int i = 0;
        do
        {
            if(parent == null)
            {
                top_left = new Vector2(0.0f, 0.0f);
                size = map_size;
            }
            else {
                size = (int)((float)parent.size * Random.Range(regionInput.generationSize.min, regionInput.generationSize.max));
                top_left = new Vector2(Random.Range(parent.top_left.x, parent.top_left.x + parent.size - size),
                    Random.Range(parent.top_left.y, parent.top_left.y + parent.size - size));
            }
            i++;
        } while(checkRegionCollision(top_left, size, parent, curRegs) && i < 2000);

        if (i == 2000)
        {
            throw new System.Exception("Fail to generate region without collision, check the region size setting");
        }

        regionInstance.top_left = top_left;
        regionInstance.size = size;
        curRegs.Add(new RegCollisionInfo(top_left, size, parent));

        if (region is CompositeRegion)
        {
            CompositeRegion compRegion = (CompositeRegion)region;
            List<RegionInstance> children = new List<RegionInstance>();

            foreach (Region childRegion in compRegion.insides)
            {
                children.Add(parseRegion(childRegion, regionInstance, curRegs));
            }

            regionInstance.children = children;
        }
        else regionInstance.children = new List<RegionInstance>();

        return regionInstance;
    }

    public string getRegionTypeString(Region region)
    {
        string full = region.GetType().ToString();
        return full.Substring(full.IndexOf('.') + 1);
    }
}
