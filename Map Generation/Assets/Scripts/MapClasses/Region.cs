//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace GeneratedClasses
{
    using System;
    using System.Collections.Generic;
    
    
    public class Region
    {
        
        public List<Region> closeTo;
        public List<Region> inside;

        public double Depth
        { get; set; }
        public double Scale
        { get; set; }

        public Region()
        {
            closeTo = new List<Region>();
            inside = new List<Region>();
        }
    }
}
