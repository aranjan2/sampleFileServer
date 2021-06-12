# fileserver

This is a fileserver based on local filesystem. It provides an extensible design with hooks to integrate with any 3rd party fileserver
including Managed service by cloud provider like AWS, Azure etc. This is a spring boot based 
project with cli as well. This project also includes a simple cli for file upload operations.
Few other considerations in case of a web and distributed scenario are : 
###security & Privacy
  authentication/authorization-  User authentication and RBAC control
  encryption - files could be encrypted in application level based on file sensitivity
  encryption keys:- Encryption keys must be kept securely preferably a managed Key-vault
  Rate-limit - Upload API should throttle to avoid any DDos attach
  signed url- there is no download url, but in case we implement that API, uri would be signed with an expiry
  certificates:-

###Performance && Scale: 
  Caching - To improve performance of APIs like list files
  CDN:- Content delivery Network like AWS cloudfront for static downloads
  Disks replicated in multiple availability zones( datacenter) in a region
  Paritioning algorithm to split files into multiple disks
  keep Metadata into database
  
### Data Protection Compliance
   File should not be leaving regional boundary without user consent

###Availability ,Deployment & Disaster recovery:
  Regional deployment with failover to different region


  



## Installation
cd $(project_dir)
docker-compose up


## Usage

TODO: Write usage instructions

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

TODO: Write license