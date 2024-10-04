# Protobuf Filter Compilation

This repository contains the Protobuf definitions for the filter functionality. To compile the `filter.proto` file, please follow the instructions below.

## Prerequisites

Make sure you have the following installed on your machine:

- **Go** (version 1.17 or later)
- **Protobuf Compiler** (`protoc`)

You can download the Protobuf compiler from the [official Protobuf releases page](https://github.com/protocolbuffers/protobuf/releases).

## Installation

Before compiling the Protobuf files, you'll need to install the required Go packages. Run the following commands:

```bash
go get google.golang.org/protobuf@latest
go get github.com/golang/protobuf/protoc-gen-go@latest
```

## Compile the Protobuf File

Once the necessary packages are installed, you can compile the filter.proto file using the following command:
```bash
protoc -I=protos/ --go_out=. protos/filter.proto
```
## Command Breakdown

### -I=protos/
This flag specifies the import path for the `.proto` files. It tells the compiler where to look for the `.proto` files you want to use.

### --go_out=.
This flag specifies the output directory for the generated Go files. In this case, it outputs them to the current directory (`.`).

### protos/filter.proto
This is the path to the specific Protobuf file you want to compile. Make sure this file exists at the specified location.