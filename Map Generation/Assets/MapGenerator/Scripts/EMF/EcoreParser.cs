using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/**
 * parse Ecore Package into corresponding ecore file based on pre-defined syntax of ecore
 **/
public class EcoreParser : PipelineStage
{
    private static readonly string REF_PREFIX = "#//";
    private static readonly string FILE_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";


    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing ECoreGenerator-----");

        EPackage package = (EPackage)input;
        SaveEcore(package, "map.ecore");
        CreateBackgroundRegionInstance("instance.xmi");
        return input;
    }

    public static string ParseEReference(EReference reference)
    {
        string basicInfo = "<eStructuralFeatures xsi:type=\"{0}\" name=\"{1}\" eType=\"" + REF_PREFIX + "{2}\"";
        basicInfo = string.Format(basicInfo, EReference.XMI_NAME, reference.Name, reference.TargetClass.Name);

        // set additional infomation when it is not the default ones
        if(reference.UpperBound != 1)
        {
            basicInfo += string.Format(" upperBound=\"{0}\"", reference.UpperBound);
        }

        if(reference.LowerBound != 0)
        {
            basicInfo += string.Format(" lowerBound=\"{0}\"", reference.LowerBound);
        }

        if (reference.Containment)
        {
            basicInfo += string.Format(" containment=\"true\"");
        }

        if(reference.EOpposite != null)
        {
            basicInfo += string.Format(" eOpposite=\"{0}{1}/{2}\"", REF_PREFIX, reference.TargetClass.Name, reference.EOpposite.Name);
        }

        basicInfo += "/>\n";

        return basicInfo;
    }

    public static string parseEAttribute(EAttribute attr)
    {
        string basicInfo = "<eStructuralFeatures xsi:type=\"{0}\" name=\"{1}\" eType=\"{2}\"";
        basicInfo = string.Format(basicInfo, EAttribute.XMI_NAME, attr.Name, attr.Type);

        // set additional infomation when it is not the default ones
        if (attr.IsId)
        {
            basicInfo += string.Format(" iD=\"true\"");
        }

        basicInfo += "/>\n";

        return basicInfo;
    }

    public static string ParseEClass(EClass cl){
        string basicInfo = "<eClassifiers xsi:type=\"{0}\" name=\"{1}\"";
        basicInfo = string.Format(basicInfo, EClass.XMI_NAME, cl.Name);

        if (cl.IsAbstract)
        {
            basicInfo += string.Format(" abstract=\"true\"");
        }

        if(cl.ESuperType != null)
        {
            basicInfo += string.Format(" eSuperTypes=\"{0}{1}\"", REF_PREFIX, cl.ESuperType.Name);
        }
        
        //add eReferences if there is any
        if(cl.EReferences.Count > 0 || cl.EAttributes.Count > 0)
        {
            basicInfo += ">\n";
            if(cl.EReferences.Count > 0)
            {
                foreach (EReference r in cl.EReferences)
                {
                    basicInfo += "  " + ParseEReference(r);
                }
            }

            if(cl.EAttributes.Count > 0)
            {
                foreach (var attr in cl.EAttributes)
                {
                    if (attr.IsId) basicInfo += "  " + parseEAttribute(attr);
                }
            }
          
            basicInfo += " </eClassifiers>\n";
        }
        else
        {
            basicInfo += "/>\n";
        }

        return basicInfo;
    }

    public static string ParseEPackage(EPackage package)
    {
        string basicInfo = "<ecore:EPackage xmi:version=\"{0}\" xmlns:xmi=\"{1}\" xmlns:xsi=\"{2}\"" 
                         +  " xmlns:ecore=\"{3}\" name=\"{4}\" nsURI=\"{5}\" nsPrefix=\"{6}\"";
        basicInfo = string.Format(basicInfo, EPackage.XMI_VERSION, EPackage.XMLNS_XMI, EPackage.XMLNS_XSI, EPackage.XMLNS_ECORE, 
                                  package.Name, package.NsURI, package.NsPrefix);

        //add eClasses if there is any
        if (package.EClasses.Count > 0)
        {
            basicInfo += ">\n";
            foreach (EClass cl in package.EClasses)
            {
                basicInfo += " " + ParseEClass(cl);
            }
            basicInfo += "</ecore:EPackage>\n";
        }
        else
        {
            basicInfo += "/>\n";
        }
        return basicInfo;
    }

    public static void SaveEcore(EPackage package, string filename)
    {
        string path = ProjectConfig.ABSOLUTE_PROJECT_PATH + "/GraphSolver/models/";
        string packageInfo = ParseEPackage(package);

        // check and make the directories
        System.IO.Directory.CreateDirectory(path);
        System.IO.File.WriteAllText((path + filename), FILE_HEADER+packageInfo);
    }

    private static void CreateBackgroundRegionInstance(string filename)
    {
        string path = ProjectConfig.ABSOLUTE_PROJECT_PATH + "/GraphSolver/";
        string instanceDef = "<?xml version=\"1.0\" encoding=\"ASCII\"?>\r\n<map.map:{0} xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:map.map=\"map.map\" xsi:schemaLocation=\"map.map models/map.ecore\">\r\n\r\n</map.map:{0}>";
        var graphEditor = UnityEditor.EditorWindow.GetWindow<GraphEditor>("Graph Editor");

        // find the region that is not inside any other region and that is the background region
        var nonBackgroundRegions = new HashSet<string>();
        foreach(var connection in graphEditor.GetConnections()){
            if(connection.type == ConnectionType.Insides)
            {
                nonBackgroundRegions.Add(connection.outPoint.node.title);
            }
        }

        string backgroundRegionName = null;
        var nodes = graphEditor.getNodes();
        foreach (var node in nodes)
        {
            if (!nonBackgroundRegions.Contains(node.title))
            {
                backgroundRegionName = node.title;
				node.isRoot = true;
                break;
            }
        }

        if(backgroundRegionName == null)
        {
            Debug.LogError("Cannot find background region, using the first region as background...");
            backgroundRegionName = nodes[0].title;
        }

        System.IO.File.WriteAllText((path + filename), string.Format(instanceDef, backgroundRegionName));
    }

    public string GetInfo()
    {
        return "Parsing Ecore Files...";
    }
}
