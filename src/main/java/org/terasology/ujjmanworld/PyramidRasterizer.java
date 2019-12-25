/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.ujjmanworld;

import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class PyramidRasterizer implements WorldRasterizer {
    private Block stone;
    private Block brick;
    private Block grass;
    private Block temp;

    @Override
    public void initialize() {
        brick = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Brick");
        grass = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Grass");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        PyramidFacet pyramidFacet = chunkRegion.getFacet(PyramidFacet.class);
        for (Object entry : pyramidFacet.getWorldEntries().entrySet()) {
            if ((Math.random() * 10) > 5)  // change pyramid look with brick pyramid or grass pyramid
                temp = brick; //temp is a temporary block to store pyramid block of grass or brick
            else
                temp = grass;
            int x = 0, y = 0, z = 0, xOffset = -1, yOffset = 0, zOffset = -1, pyramidHeight = (int) (Math.random() * 40); // ra is for different heights of pyramids
            for (y = yOffset; y < pyramidHeight; y++) {   // y is for height of pyramid
                zOffset++;   //increasing zOffset so that z axis increases to get triangle shape
                xOffset++;   // increasing xOffset so that x axis increases and gets triangle shape
                for (z = zOffset; z < pyramidHeight - zOffset; z++) {  // z is for z axis of pyamid
                    for (x = xOffset; x < pyramidHeight - xOffset; ++x) {  // x is for x axis of pyramid
                        chunk.setBlock(x, y, z, temp);  //setting block of pyramid
                    }
                }
            }
        }
    }
}