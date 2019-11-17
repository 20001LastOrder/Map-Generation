using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EClass
{
    public static readonly string XMI_NAME = "ecore:EClass";

    private bool isAbstract = false;
    private EClass eSuperType;

    public EClass(string name)
    {
        this.Name = name;
        EReferences = new List<EReference>();
        EAttributes = new List<EAttribute>();
    }

    public string Name
    {
        get;
        set;
    }

    public List<EReference> EReferences
    {
        get;
    }

    public List<EAttribute> EAttributes
    {
        get;
    }

    public bool IsAbstract
    {
        get => isAbstract;
        set { isAbstract = value; }
    }

    public EClass ESuperType
    {
        get => eSuperType;
        set {
            if(value != this)
            {
                eSuperType = value;
            }
        }
    }
}
