using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/**
 * parse Ecore Package into corresponding ecore file based on pre-defined syntax of ecore
 **/
public static class EcoreParser
{
    private static readonly string REF_PREFIX = "#//";
    private static readonly string FILE_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

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

        if (reference.Conptainment)
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
        if(cl.EReferences.Count > 0)
        {
            basicInfo += ">\n";
            foreach (EReference r in cl.EReferences)
            {
                basicInfo += "  "+ParseEReference(r);
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

    public static void SaveEcore(EPackage package)
    {
        string path = Application.dataPath + "/GraphSolver/models/";
        string packageInfo = ParseEPackage(package);
        System.IO.File.WriteAllText((path + "model.ecore"), FILE_HEADER+packageInfo);
    }
}
