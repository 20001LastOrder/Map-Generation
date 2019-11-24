using System;
using System.Reflection;
using System.IO;
using System.CodeDom;
using System.CodeDom.Compiler;
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
            targetClass = new CodeTypeDeclaration(className);
            targetClass.IsClass = true;
            targetClass.TypeAttributes = TypeAttributes.Public;
            samples.Types.Add(targetClass);
            targetUnit.Namespaces.Add(samples);
        }

        /// <summary>
        /// Adds field to the class.
        /// </summary>
        public void AddField(string name, string type)
        {
            Type fieldType = typeof(Type);
            switch (type)
            {
                case "int":
                    fieldType = typeof(int);
                    break;
                case "double":
                    fieldType = typeof(double);
                    break;
                case "string":
                    fieldType = typeof(string);
                    break;
                case "bool":
                    fieldType = typeof(bool);
                    break;
            }

            CodeMemberField field = new CodeMemberField();
            field.Attributes = MemberAttributes.Private;
            field.Name = name;
            field.Type = new CodeTypeReference(fieldType);
            targetClass.Members.Add(field);
        }
        /// <summary>
        /// Add property to the class
        /// </summary>
        public void AddProperty(string name, string type)
        {
            Type propType = typeof(Type);
            switch (type)
            {
                case "int":
                    propType = typeof(int);
                    break;
                case "double":
                    propType = typeof(double);
                    break;
                case "string":
                    propType = typeof(string);
                    break;
                case "bool":
                    propType = typeof(bool);
                    break;
            }

            CodeMemberProperty property = new CodeMemberProperty();
            property.Attributes =
                MemberAttributes.Public | MemberAttributes.Final;
            property.Name = name;
            property.HasGet = true;
            property.HasSet = true;
            property.Type = new CodeTypeReference(propType);
            property.GetStatements.Add(new CodeMethodReturnStatement(
                new CodeFieldReferenceExpression(
                    new CodeThisReferenceExpression(), name)));
            property.SetStatements.Add(new CodeAssignStatement(
                new CodeFieldReferenceExpression(
                    new CodeThisReferenceExpression(), name), new CodePropertySetValueReferenceExpression()));
            targetClass.Members.Add(property);
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

            foreach (CodeTypeMember member in targetClass.Members)
            {
                if (member is CodeMemberField)
                {
                    // Add parameters
                    CodeMemberField field = (CodeMemberField)member;
                    constructor.Parameters.Add(new CodeParameterDeclarationExpression(
                        Type.GetType(field.Type.BaseType), field.Name));
                    CodeFieldReferenceExpression fieldRef =
                        new CodeFieldReferenceExpression(
                        new CodeThisReferenceExpression(), field.Name);
                    constructor.Statements.Add(new CodeAssignStatement(fieldRef,
                        new CodeArgumentReferenceExpression(field.Name)));
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

        /// <summary>
        /// Create the CodeDOM graph and generate the code.
        /// </summary>
        static void Main()
        {
            ClassGenerator sample = new ClassGenerator("randomclass");
            sample.AddField("random", "int");
            sample.AddProperty("random", "int");
            sample.AddConstructor();
            sample.GenerateCSharpCode(outputFileName);
        }
    }
}