using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EReference
{
    public static readonly string XMI_NAME = "ecore:EReference";

    private string name;    
    private EClass eType;
    private int upperBound = 1;
    private int lowerBound = 0;
    private bool conptainment = false;

    public EReference(string name, EClass targetClass)
    {
        this.name = name;
        this.eType = targetClass;
    }

    public string Name
    {
        get => name;
        set { name = value; }
    }

    public EClass EType
    {
        get => eType;
        set { eType = value; }
    }

    public int UpperBound
    {
        get => upperBound;
        set {
            if(value == -1 || (value >= lowerBound && value > 0))
            {
                upperBound = value;
            }
        }
    }

    public int LowerBound
    {
        get => lowerBound;
        set {
            if(upperBound == -1 || (value <= upperBound && value >= 0))
            {
                lowerBound = value;
            }
        }
    }

    public bool Conptainment
    {
        get => conptainment;
        set { conptainment = value; }
    }
}
