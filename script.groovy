def returncreds(){
def map = [:]
map["AWS_ACCOUNT_ID"] = 653709203391
map["AWS_DEFAULT_REGION"]="us-east-1" 
map["IMAGE_REPO_NAME"]="jenkinspipleine"
map["REPOSITORY_URI"] = "$map[AWS_ACCOUNT_ID].dkr.ecr.$map[AWS_DEFAULT_REGION].amazonaws.com/$map[IMAGE_REPO_NAME]"
 

return map
}

return this
