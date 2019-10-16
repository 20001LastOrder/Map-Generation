using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EClass
{
    public static readonly string XMI_NAME = "ecore:EClass";

    private string name;
    private List<EReference> eReferences;
    private bool isAbstract = false;
    private EClass eSuperType;

    public EClass(string name)
    {
        this.name = name;
        eReferences = new List<EReference>();
    }

    public string Name
    {
        get => name;
        set { name = value; }
    }

    public List<EReference> EReferences
    {
        get => eReferences;
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
