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
    return 80; // snow
}
function getFillerBlock(){
    return 79; // ice
}

function getSingleBiome(){
    return this;
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