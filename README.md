# A8: Build a Chatroom
## Entry point
We separated the assignment into two programs, the client program and the server program. The entry points are ClientDriver.main() and ServerDriver.main().
## Key classes/methods descriptions in client program
#### Client class
* send thread: send message data to output stream from input.
* receive thread: receive response data from input stream from server.
#### Message classes
* writeToStream(): this method can write data to the output stream following the protocol and server will receive and read it.
## Key classes/methods descriptions in server program
#### Server class
* channel thread: deal with the connected clients.
#### Operation class
* different response methods: call different response class to write data to output stream.
#### Response classes
* writeToStream(): this method can write data to output stream following the protocol and client will receive and read it.
## Assumptions
* The server and the client share the identical protocol.
## Steps to ensure correctness
Through tests.
## Quick start
The ip address and the port can be modified. Start ServerDriver.main() and ClientDriver.main() to run the program.
## Client output
![Alt Text](https://raw.github.ccs.neu.edu/cs5010seaF19/group_chengfeng_suhao/Assignment-8-Final/Assignment8/Readme_ClientImg.png?token=AAABOATIS3RDOAZ3ZNMFS2256CCVY)
