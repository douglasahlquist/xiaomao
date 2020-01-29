#!/bin/bash

#  API key:        r6LYCkN17ZRjUVUA4lioXgXZH
#  API secret key: 1pgn7wl2hDUg8PKap8UDEdit5XnYkDMiawvJs5a5KXg5Xvk33a


export OAUTH_ACCESS_TOKEN=1pgn7wl2hDUg8PKap8UDEdit5XnYkDMiawvJs5a5KXg5Xvk33a

#  https://api.twitter.com/oauth/authorize?oauth_token=Z6eEdO8MOmk394WozF5oKyuAv855l4Mlqo7hhlSLik

curl -X POST https://api.twitter.com/oauth/authorize&Name=douglasahlquist?oauth_token=$OAUTH_TOKEN 
