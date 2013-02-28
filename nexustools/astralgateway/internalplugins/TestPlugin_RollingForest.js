//importPackage("nexustools.astralgateway.api");

//function getReplacements(){ // not used yet, but can later be used to return a list of from->to post-processing replacement blockds
//    
//}

function isSingleBiome(){
    return true;
}

function isBiome(){ // return true if this source is a biome
    return true;
}

function getTopBlock(){
    return 2; // grass
}

function getTemperature(){
    return 1;
}

function getSimplexResolution(){
    return 2048;
}

function getFillerBlock(){
    return 3; // dirt
}

function getSingleBiome(){
    return this;
}

function getPluginName(){
    return "Test world provider plugin (RollingForest)";
}

function getName(){ // world provider calls this
    return "Test - Rolling Forest";
}

function init(){}
