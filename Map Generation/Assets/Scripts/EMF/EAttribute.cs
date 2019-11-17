using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EAttribute
{
    public static readonly string XMI_NAME = "ecore:EAttribute";

    public string Name { get; set; }
    public string Type { get; set; }

    public bool IsId { get; set; }

    public string DefaultValue { get; set; }

    public EAttribute(string name, string type)
    {
        this.Name = name;
        this.Type = type;
    }
}
