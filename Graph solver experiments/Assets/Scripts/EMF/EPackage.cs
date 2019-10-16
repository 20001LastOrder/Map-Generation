using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EPackage
{
    public static readonly string XMI_VERSION = "2.0";
    public static readonly string XMLNS_XMI = "http://www.omg.org/XMI";
    public static readonly string XMLNS_XSI = "http://www.w3.org/2001/XMLSchema-instance";
    public static readonly string XMLNS_ECORE = "http://www.eclipse.org/emf/2002/Ecore";

    private string name;
    private string nsURI;
    private string nsPrefix;
    private List<EClass> eClasses;

    public EPackage(string name)
    {
        this.name = name;
        nsPrefix = "map." + name.ToLower();
        nsURI = "map." + name.ToLower();
        eClasses = new List<EClass>();
    }

    public string Name
    {
        get => name;
        set { name = value; }
    }

    public List<EClass> EClasses  => eClasses;

    public string NsURI => nsURI;

    public string NsPrefix => nsPrefix;
}
