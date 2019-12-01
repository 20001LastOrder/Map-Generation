using System;
using System.Reflection;
using System.IO;
using System.CodeDom;
using System.CodeDom.Compiler;
using System.Collections.Generic;
using Microsoft.CSharp;

namespace CodeGenerator
{
    /// <summary>
    /// This code example creates a graph using a CodeCompileUnit and  
    /// generates source code for the graph using the CSharpCodeProvider.
    /// </summary>
    class ClassGenerator
    {
        /// <summary>
        /// Define the compile unit to use for code generation. 
        /// </summary>
        CodeCompileUnit targetUnit;

        /// <summary>
        /// The only class in the compile unit. This class contains 2 fields,
        /// 3 properties, a constructor, an entry point, and 1 simple method. 
        /// </summary>
        CodeTypeDeclaration targetClass;

        /// <summary>
        /// The name of the file to contain the source code.
        /// </summary>
        private const string outputFileName = "SampleCode.cs";

        /// <summary>
        /// Define the class.
        /// </summary>
        public ClassGenerator(string className)
        {
            targetUnit = new CodeCompileUnit();
            CodeNamespace samples = new CodeNamespace("GeneratedClasses");
            samples.Imports.Add(new CodeNamespaceImport("System"));
            samples.Imports.Add(new CodeNamespaceImport("System.Collections.Generic"));
            targetClass = new CodeTypeDeclaration(className);
            targetClass.IsClass = true;
            targetClass.TypeAttributes = TypeAttributes.Public;
            samples.Types.Add(targetClass);
            targetUnit.Namespaces.Add(samples);
        }

        /// <summary>
        /// Adds association to specified class.
        /// </summary>
        public void AddReference(string className, string refName, int multiplicity)
        {
            CodeMemberField association = new CodeMemberField();
            association.Attributes = MemberAttributes.Public;
            association.Name = refName;
            if (multiplicity == -1)
            {  
                association.Type = new CodeTypeReference("List<"+className+">");
            }
            else
            {
                association.Type = new CodeTypeReference(className);
            }
            targetClass.Members.Add(association);
        }

        /// <summary>
        /// Adds field to the class.
        /// </summary>
        public void AddField(string name, string type)
        {
            Type sType = typeof(int); ;
            switch (type)
            {
                case "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDouble":
                    sType = typeof(double);
                    break;
                case "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString":
                    sType = typeof(string);
                    break;
                case "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean":
                    sType = typeof(bool);
                    break;
            }

            CodeMemberField field = new CodeMemberField();
            field.Attributes = MemberAttributes.Private;
            field.Name = name;
            field.Type = new CodeTypeReference(sType);
            targetClass.Members.Add(field);
        }
        /// <summary>
        /// Add property to the class
        /// </summary>
        public void AddProperty(string name, string type)
        {
            Type sType = typeof(int);
            switch (type)
            {
                case "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDouble":
                    sType = typeof(double);
                    break;
                case "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString":
                    sType = typeof(string);
                    break;
                case "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean":
                    sType = typeof(bool);
                    break;
            }

            CodeMemberProperty property = new CodeMemberProperty();
            property.Attributes =
                MemberAttributes.Public | MemberAttributes.Final;
            property.Name = name;
            property.HasGet = true;
            property.HasSet = true;
            property.Type = new CodeTypeReference(sType);
            property.GetStatements.Add(new CodeMethodReturnStatement(
                new CodeFieldReferenceExpression(
                    new CodeThisReferenceExpression(), name)));
            property.SetStatements.Add(new CodeAssignStatement(
                new CodeFieldReferenceExpression(
                    new CodeThisReferenceExpression(), name), new CodePropertySetValueReferenceExpression()));
            targetClass.Members.Add(property);
        }

        /// <summary>
        /// Adds a supertype to the class.
        /// </summary>
        public void AddSupertype(string className)
        {
            targetClass.BaseTypes.Add(new CodeTypeReference(className));
        }

        /// <summary>
        /// Add a constructor to the class.
        /// </summary>
        public void AddConstructor()
        {
            // Declare the constructor
            CodeConstructor constructor = new CodeConstructor();
            constructor.Attributes =
                MemberAttributes.Public | MemberAttributes.Final;

            List<CodeTypeReference> fieldTypes = new List<CodeTypeReference>();
            fieldTypes.Add(new CodeTypeReference(typeof(bool)));
            fieldTypes.Add(new CodeTypeReference(typeof(int)));
            fieldTypes.Add(new CodeTypeReference(typeof(double)));
            fieldTypes.Add(new CodeTypeReference(typeof(string)));

            foreach (CodeTypeMember member in targetClass.Members)
            {
                if (member is CodeMemberField)
                {
                    // Add parameters
                    CodeMemberField field = (CodeMemberField)member;
                    if (fieldTypes.Contains(field.Type))
                    {
                        constructor.Parameters.Add(new CodeParameterDeclarationExpression(
                            field.Type, field.Name));
                        CodeFieldReferenceExpression fieldRef =
                            new CodeFieldReferenceExpression(
                            new CodeThisReferenceExpression(), field.Name);
                        constructor.Statements.Add(new CodeAssignStatement(fieldRef,
                            new CodeArgumentReferenceExpression(field.Name)));
                    }
                }
            }

            targetClass.Members.Add(constructor);
        }

        /// <summary>
        /// Generate CSharp source code from the compile unit.
        /// </summary>
        /// <param name="filename">Output file name</param>
        public void GenerateCSharpCode(string fileName)
        {
            CodeDomProvider provider = CodeDomProvider.CreateProvider("CSharp");
            CodeGeneratorOptions options = new CodeGeneratorOptions();
            options.BracingStyle = "C";
            using (StreamWriter sourceWriter = new StreamWriter(fileName))
            {
                provider.GenerateCodeFromCompileUnit(
                    targetUnit, sourceWriter, options);
            }
        }
    }
}