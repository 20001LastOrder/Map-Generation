using CodeGenerator;
using System;
using UnityEditor;
using UnityEngine;

public class Parser : PipelineStage
{
    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing Code Generation-----");
        EPackage ePackage = (EPackage)input;

        ECoreToCS(ePackage, ProjectConfig.ABSOLUTE_PROJECT_PATH + "/Scripts/MapClasses");
        return input;
    }

    public void ECoreToCS(EPackage ePackage, String destinationFolder) 
    {
        foreach (EClass eClass in ePackage.EClasses)
        {
            if (eClass.Name == "Map" || eClass.Name == "Grid" || eClass.Name == "GridType")
            {
                continue;
            }
            ClassGenerator classGen = new ClassGenerator(eClass.Name);
            if (eClass.ESuperType != null)
            {
                classGen.AddSupertype(eClass.ESuperType.Name);
            }

            foreach (EReference eRef in eClass.EReferences)
            {
                classGen.AddReference(eRef.TargetClass.Name, eRef.Name, eRef.UpperBound);
            }

            foreach (EAttribute eAttribute in eClass.EAttributes)
            {
                classGen.AddField("_" + eAttribute.Name, eAttribute.Type);
                classGen.AddProperty(System.Globalization.CultureInfo.CurrentUICulture.TextInfo.ToTitleCase(eAttribute.Name), eAttribute.Type);
            }
            
            classGen.AddConstructor();
            classGen.GenerateCSharpCode(destinationFolder + "\\" + eClass.Name + ".cs");
        }

		GraphEditor.Instance.WaitForReload();
        AssetDatabase.Refresh();
    }

    public string GetInfo()
    {
        return "Generating Region Classes...";
    }
}
