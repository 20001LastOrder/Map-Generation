using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

public class ConstraintParser : PipelineStage
{
    public object execute(object input)
    {
        EPackage package = (EPackage)input;
        GraphEditor graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");

        // check and make the directories
        string path = ProjectConfig.ABSOLUTE_PROJECT_PATH + "/GraphSolver/queries/";
        System.IO.Directory.CreateDirectory(path);
        ParseConstraintOnPackage(package, graphEditor, path + "queries.vql");
        return input;
    }

    public void ParseConstraintOnPackage(EPackage package, GraphEditor graph, string outputFilename)
    {
        var constraints = "";
        constraints += GenerateHeaderInformation(package);
        constraints += GenerateIdentificationQuery(package);
        constraints += GenerateInsideConstraint(package, graph.getNodes(), graph.GetConnections());
        //constraints += GenerateCloseConstraint(package, graph.GetConnections());

        //// same parent close
        //constraints += "@Constraint(key={a,b}, severity=\"error\", message=\"error\")"
        //                +"pattern outerClose(a: Region, b: Region){\n"
        //                +"Region.close(a, b);\n"
        //                +"CompositeRegion.insides(R1, a);\n"
        //                +"CompositeRegion.insides(R2, b);\n"
        //                +"R1 != R2;\n"
        //                + "}\n";
        System.IO.File.WriteAllText(outputFilename, constraints);

		if (graph.getExtraConstraintFilePath() != null && !graph.getExtraConstraintFilePath().Trim().Equals("")) {
			string content = System.IO.File.ReadAllText(graph.getExtraConstraintFilePath());
			System.IO.File.AppendAllText(outputFilename, content);
		}
    }

    private static string GenerateHeaderInformation(EPackage package)
    {
        var header = "package queries \n";
        header += "import epackage \"" + package.NsPrefix + "\"\n\n";
        return header;
    }

    private static string GenerateIdentificationQuery(EPackage package)
    {
        var queries = "";
        foreach(EClass cl in package.EClasses){
            if (cl.IsAbstract)
            {
                continue;
            }

            var parent = FindTopParent(cl);

            //query definitions
            queries += "pattern is" + cl.Name + "(a:" + parent.Name + "){\n";
            queries += "\t" + cl.Name + "(a);\n";
            queries += "}\n";
        }

        return queries;
    }

    private static string GenerateInsideConstraint(EPackage package, List<Node> nodes, List<Connection> connections)
    {
        var composites = new Dictionary<string, List<string>>();
        var constraints = "";
        //find all the composite classes
        foreach(var node in nodes)
        {
            if (node.isComposite)
            {
                composites.Add(node.title, new List<string>());
            }
        }

        // find all regions that inside another region
        foreach(var connection in connections)
        {
            if(connection.type == ConnectionType.Insides)
            {
                composites[connection.inPoint.node.title].Add(connection.outPoint.node.title);
            }
        }

        foreach(var name in composites.Keys)
        {
            constraints += "@Constraint(key={a}, severity=\"error\", message=\"error\")\n";
            constraints += "pattern " + name + "Inside(a: " + name + "){\n";
            constraints += "\t"+name + ".insides(a,b);\n";
            foreach(var component in composites[name])
            {
                constraints += "\tneg find is" + component + "(b);\n";
            }
            constraints += "}\n";
        }

        return constraints;
    }

    private static string GenerateCloseConstraint(EPackage package, List<Connection> connections)
    {
        var constraints = "";
        foreach (var cl in package.EClasses)
        {
            if (cl.IsAbstract)
            {
                continue;
            }
            var parent = FindTopParent(cl);
            constraints += "@Constraint(key={a, b}, severity=\"error\", message=\"error\")\n";
            constraints += "pattern " + cl.Name + "Close(a: " + cl.Name + ", b: " + parent.Name + "){\n";
            constraints += "\t" + cl.Name + ".close(a,b);";

            var closeConnections= connections.FindAll(n => n.type == ConnectionType.Next && n.outPoint.node.title.Equals(cl.Name));
            foreach(var connect in closeConnections)
            {
                constraints += "\t neg find is" + connect.inPoint.node.title + "(b);\n";
            }
            constraints += "}\n";
        }

        return constraints;
    }

    private static EClass FindTopParent(EClass cl)
    {
        while(cl.ESuperType != null)
        {
            cl = cl.ESuperType;
        }
        return cl;
    }

    public string GetInfo()
    {
        return "Pasring Constraints...";
    }
}
