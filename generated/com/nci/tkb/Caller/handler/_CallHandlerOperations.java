// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `Caller.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.nci.tkb.Caller.handler;

public interface _CallHandlerOperations
{
    void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt, Ice.Current __current);

    java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm, Ice.Current __current);

    java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm, Ice.Current __current);

    java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, Ice.Current __current);

    void shutdown(Ice.Current __current);
}