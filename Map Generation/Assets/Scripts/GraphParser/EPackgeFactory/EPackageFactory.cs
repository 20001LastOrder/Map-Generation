using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

public class EPackageFactory : PipelineStage
{
    public void execute()
    {
        Debug.Log("Executing EPackageFactory");
        
        GraphEditor graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");

        List<EClass> eclassList = new List<EClass>();
        foreach(Node node in graphEditor.getNodes())
        {
            EClass eClass = new EClass(node.title);

            // TODO: fill rest of attributes

            eclassList.Add(eClass);
        }
    }
}
