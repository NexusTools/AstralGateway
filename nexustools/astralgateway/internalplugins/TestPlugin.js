//importPackage("nexustools.astralgateway.api");

//function getReplacements(){ // not used yet, but can later be used to return a list of from->to post-processing replacement blockds
//    
//}

//function isSingleBiome(){
//    return true;
//}
//function isBiome(){ // return true if this source is a biome
//    return true;
//}

//function getStoneReplacement(){
//    return 11; //stationary lava
//}
//
//function getTopBlock(){
//    return 1; // rock
//}
//function getFillerBlock(){
//    return 49; // obsidian
//}

//function getSingleBiome(){
//    return this;
//}

function getSimplexResolution(){
    println("getting simplex resolution");
    return 128;
}

function getPluginName(){
    return "Test world provider plugin";
}

function getName(){ // world provider calls this
    return "JSTestWorld";
}

function init(){
    print("weee test is working\n");
}