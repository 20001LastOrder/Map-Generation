using System.Collections;
using System.Collections.Generic;
using GeneratedClasses;
public static class MapShapeGenerator
{
    // generate a default square map shape
    /*public static Map GenerateDefaultShape(int length, int width)
    {
        Map map = new Map();
        List<GeneratedClasses.Grid> grids = new List<GeneratedClasses.Grid>();

        // create all the grids first
        for(var i = 0; i < length * width; i++)
        {
            grids.Add(new GeneratedClasses.Grid(i+1));
        }

        //assign references to keep squared shape
        for(var i = 0; i < length * width; i++)
        {
            //left most node
            if(i % width != 0)
            {
                grids[i].Left = grids[i - 1];
            }

            if(i % width != width - 1)
            {
                grids[i].Right = grids[i + 1];
            }

            if(i / width != 0)
            {
                grids[i].Up = grids[i - width];
            }

            if (i / width != length - 1) {
                grids[i].Down = grids[i + width];
            }
        }

        map.Grids = grids;
        return map;
    }*/
}
