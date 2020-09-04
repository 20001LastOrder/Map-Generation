using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

public class GenerationConfigRunner : PipelineStage
{
    const string PREFIX = "import epackage \"models/Map.ecore\"\r\nimport viatra \"queries/queries.vql\"\r\ngenerate {\r\n\tmetamodel = {package map}\r\n\tpartial-model = { \"instance.xmi\"}\r\n\tconstraints = { package queries }\r\n\tsolver = ViatraSolver\r\n\tscope = {\r\n";
    const string SUFFIX = "\r\n\t}\r\n\tnumber = 1\r\n\truns = 1\r\n\tconfig = {\r\n\t\tlog-level = normal\r\n\t}\r\n\tdebug = \"debug\"\r\n\toutput = \"output/\"\r\n}";
    const string FILE_NAME = "/GraphSolver/map.vsconfig";

    public object execute(object input)
    {
        //TODO: read ranges from graph editor
        var scopeConfig = new Dictionary<string, string>();
        var graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");
        //input is not changed
        foreach(var node in graphEditor.getNodes())
        {
			// only contains one root
			if (node.isRoot) {
				continue;
			}

            int min = node.generationRange.min;
            int max = node.generationRange.max;
            if (max != min)
            {
                scopeConfig.Add(node.title, min + ".." + max);
            }
            else
            {
                scopeConfig.Add(node.title, min.ToString());
            }
           
        }
        WriteConfig(ProjectConfig.ABSOLUTE_PROJECT_PATH + FILE_NAME, scopeConfig);
        return input;
    }

    public string GetInfo()
    {
        return "Generating Configuration Files...";
    }

    public void WriteConfig(string filename, Dictionary<string, string> configuration)
    {
        // set scope configuration
        string scope = "";
        var i = 0;
        foreach(var key in configuration.Keys)
        {
            scope += "\t\t#<" + key + "> += " + configuration[key];
            if(i != configuration.Keys.Count - 1)
            {
                scope += ",";
            }
            scope += "\r\n";
            i++;
        }

        string configContent = PREFIX + scope + SUFFIX;
        System.IO.File.WriteAllText(filename, configContent);
    }

}
