using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EAttribute
{
    public string Name { get; set; }
    public string Type { get; set; }

    public string Value { get; set; }

    public EAttribute(string name, string type)
    {
        this.Name = name;
        this.Type = type;
    }
}
