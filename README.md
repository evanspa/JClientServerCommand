JClientServerCommand
====================

A Java-based library implementing the GoF 'Command' design pattern within a client/server application topology.

## History and Current State

This code was written about 2008 and is no longer maintained.

## Rationale

This code was developed as part of an exploration of document-oriented SOAP-based web services; more specifically building an abstraction layer on top of the web service interaction, providing distribution transparency.  The [Gang-of-Four](http://en.wikipedia.org/wiki/Design_Patterns) 'Command' design pattern was chosen as the basis for the abstraction.

## Overview

This project presents a Java based framework for creating distributed or local command objects.

Using this framework, a client program creates a command object, sets any data that needs to be set on it and then invokes its "execute()" method. Classic GoF command design pattern. The difference is in how the execute() method is implemented. The implementation of the execute() method does not contain business logic; instead it invokes a "client command delegate" collaborator object. This delegate object is responsible for serializing the state of the command object and sending it to some endpoint location. At the endpoint the command object will be reconstituted into a command object and its execute() method will be invoked. Only this time the implementation of the execute() method really does contain the business logic. This works because the consuming application actually has a "client command" object and the endpoint application de-serializes it into a "server command" object. Both the client and server command classes extend from a common interface (i.e. they are compatible with each other from a type perspective).

The client application will interact with the command object via its interface, however it's implementation will be that of a "client command" object; one whose execute() implementation is to invoke a delegate. When the endpoint receives the serialized command and is reconstituted, the endpoint (like the client application does) will interact with the command object via its interface, however its implementation will be that of a "server command" object; one whose execute() implementation contains the actual business logic.

Here's what the framework provides:

+ A generic command interface from which new commands can extend; classes implementing the extended interface would implement its business logic within the execute() method 
+ A client command interface from which a client command class should implement; the implementation of the execute() method should be to invoke a delegate + 2 client delegate implementations (a delegate is what the client command's execute() method invokes): a local one in which the client and server command instances run within the same JVM, and one in which the command is serialized into a SOAP message and some web service endpoint is invoked. + An Spring-based SOAP marshaling endpoint class that can received XML serialized command instances, un-marshal them into corresponding command instances, execute them and return the results in a SOAP response.
