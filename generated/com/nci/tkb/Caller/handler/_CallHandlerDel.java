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

public interface _CallHandlerDel extends Ice._ObjectDel
{
    void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;

    java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;

    java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;

    java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;

    void shutdown(java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;
}