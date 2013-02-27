/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.terrain;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 *
 * @author luke
 */
public class SimplexProvider extends net.minecraft.world.gen.ChunkProviderGenerate{
    
    public static World world;
    
//    public SimplexProvider(){
//        super(null,0,false);
//    }
    
//    public void update(World w){
//        world = w;
//    }
    
    public SimplexProvider(World w, long l, boolean b){
        super(w,l,b);
        world =w;
//        is.generatePerm(w.getSeed());
        is.generatePerm(118881117253L);
    }
    
    boolean single = false;
    
    public void setSingle(boolean b){
        single = b;
    }
    
    CustomBiome singledata = null;
    
    public void setSingleBiome(CustomBiome b){
        singledata = b;
    }
    
//    List creets = new ArrayList();
//
//    @Override
//    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
//        return creets;
//    }

    static BiomeGenBase[] tmp = null;
    
    InstancedSimplex is = new InstancedSimplex();
        
    float trg = 0.5f;
    
    @Override
    public Chunk provideChunk(int chunkx, int chunky) {
        short[] blocks = new short[16*16*255];
        byte[] metas = new byte[16*16*255];
        int v = 0;
        int b = 0;
        short topblock = 0;
        short filler = 0;
        float nlx = 0;
        float nlz = 0;
        
        if(!single)
            tmp = world.getWorldChunkManager().loadBlockGeneratorData(tmp, chunkx* 16, chunky * 16, 16, 16);
       
        try{
            for(int x = 0; x < 16; x++){
                for(int z = 0; z < 16; z++){
                    b = x + z * 16;
                    nlx = ((float)(x+(chunkx*16)))/(256f);
                    nlz = ((float)(z+(chunky*16)))/(256f);
                    if(!single&&b>=tmp.length){
                        System.out.println("FUUUUUUU ERRROR D: biome coordinate out of bounds: " + b + ", limit: " + tmp.length);
                    }
                    topblock = single?singledata.topBlock:tmp[b].topBlock;
                    filler = single?singledata.fillerBlock:tmp[b].fillerBlock;
//                    if(trg < tmp[b].maxHeight) trg += 0.0002;
//                    else if(trg > tmp[b].maxHeight) trg -= 0.0002;
                    for(int y = 255; y > 0; y--){
                        v = y << 8 | z << 4 | x;
                        if(
                                is.noise(nlx, nlz)>((float)y-128)/(128f) &&
                                !(is.noise(nlx, y/128f, nlz)>0.5f) &&
                                !(is.noise(nlz, y/16f, nlx)>0.6f)
                        ){
                            try{
                                if(y<255&&blocks[(y+1)<< 8 | z << 4 | x]==0){
                                    blocks[v] = topblock;
                                }else if(y>250||blocks[(y+5)<< 8 | z << 4 | x]==0){
                                    blocks[v] = filler;
                                }else{
                                    blocks[v] = 1;
                                }
                            }catch(Throwable e){System.out.println("Generation error at location " + x + ", " + y + ", " + z);}
                        }else if(y<32){
                            blocks[v] = (short)Block.waterStill.blockID;
                        }
                    }
                }
            }
        }catch(Throwable e){System.out.println("OMFG SIMPLEXPROVIDER ENCOUNTERED AN ERROR! WTF IS THIS?");e.printStackTrace();}
        
        Chunk ret = new Chunk(world, blocks, metas, chunkx, chunky);
        
        ret.generateSkylightMap();
        
        return ret;
    }
    
}
