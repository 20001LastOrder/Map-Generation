﻿using CodeGenerator;
using System;

public static class Parser
{

    public static void ECoreToCS(EPackage ePackage, String destinationFolder) 
    {
        foreach (EClass eClass in ePackage.EClasses)
        {
            ClassGenerator classGen = new ClassGenerator(eClass.Name);
            foreach (EAttribute eAttribute in eClass.EAttributes)
            {
                classGen.AddField(eAttribute.Name, eAttribute.Type);
                classGen.AddProperty(eAttribute.Name, eAttribute.Type);
            }
            
            classGen.AddConstructor();
            classGen.GenerateCSharpCode(destinationFolder + "\\" + eClass.Name + ".cs");
        }
    }
}