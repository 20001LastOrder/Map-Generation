using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GraphSolverRunner : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        // create classes
        EClass map = new EClass("Map");
        EClass grid = new EClass("Grid")
        {
            IsAbstract = true
        };
        EClass city = new EClass("City");
        EClass mountain = new EClass("Mountain");
        EClass village = new EClass("Village");
        EClass water = new EClass("Water");


        grid.EReferences.Add(new EReference("left", grid));
        grid.EReferences.Add(new EReference("right", grid));
        grid.EReferences.Add(new EReference("up", grid));
        grid.EReferences.Add(new EReference("down", grid));

        city.ESuperType = grid;
        mountain.ESuperType = grid;
        village.ESuperType = grid;
        water.ESuperType = grid;

        EReference r = new EReference("grids", grid)
        {
            Conptainment = true,
            UpperBound = -1
        };
        map.EReferences.Add(r);

        EPackage package = new EPackage("map");
        package.EClasses.Add(map);
        package.EClasses.Add(grid);
        package.EClasses.Add(city);
        package.EClasses.Add(mountain);
        package.EClasses.Add(village);
        package.EClasses.Add(water);


        EcoreParser.SaveEcore(package);
        Debug.Log("finished creating package");
        runSolver();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void runSolver()
    {
        string path = Application.dataPath + "/GraphSolver/";
        System.Diagnostics.Process process = new System.Diagnostics.Process();
        System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo();
        startInfo.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
        startInfo.WorkingDirectory = path;
        startInfo.FileName = "java";
        startInfo.Arguments = "-jar app.jar map.vsconfig";
        startInfo.RedirectStandardOutput = true;
        startInfo.UseShellExecute = false;
        startInfo.CreateNoWindow = true;
        process.StartInfo = startInfo;
        process.Start();
        string output = process.StandardOutput.ReadToEnd();
        Debug.Log(output);
        process.WaitForExit();
        Debug.Log("Done");
    }
}
