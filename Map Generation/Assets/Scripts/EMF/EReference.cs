using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EReference
{
    public static readonly string XMI_NAME = "ecore:EReference";

    private string name;    
    private EClass targetClass;
    private int upperBound = 1;
    private int lowerBound = 0;
    private EReference eOpposite;
    private bool conptainment = false;

    public EReference(string name, EClass targetClass)
    {
        this.name = name;
        this.targetClass = targetClass;
    }

    public string Name
    {
        get => name;
        set { name = value; }
    }

    public EClass TargetClass
    {
        get => targetClass;
        set { targetClass = value; }
    }

    public EReference EOpposite
    {
        get => eOpposite;
        set {
            if (targetClass.EReferences.Contains(value))
            {
                eOpposite = value;
                if(eOpposite.EOpposite != this)
                {
                    eOpposite.EOpposite = this;
                }
            }
            else
            {
                throw new System.Exception("Assigned EOpposite is not belongs to the target class");
            }
        }
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

    public bool Containment
    {
        get => conptainment;
        set { conptainment = value; }
    }
}
