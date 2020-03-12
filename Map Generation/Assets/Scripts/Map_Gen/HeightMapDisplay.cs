using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HeightMapDisplay : PipelineStage
{

    public System.Object execute(System.Object input)
    {
        float[,] heightMap = (float[,])input;

        GameObject plane = GameObject.Find("NoiseMapPlane");
        Texture2D noiseTexture = TextureGenerator.TextureFromHeightMap(heightMap);

        Renderer textureRender = plane.GetComponent<Renderer>();
        textureRender.sharedMaterial.mainTexture = noiseTexture;
        textureRender.transform.localScale = new Vector3(noiseTexture.width, 1, noiseTexture.height);

        return null;
    }
}
