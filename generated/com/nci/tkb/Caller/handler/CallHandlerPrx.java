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

public interface CallHandlerPrx extends Ice.ObjectPrx
{
    public void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt);

    public void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm);

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, Ice.Callback __cb);

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, Callback_CallHandler_getResult1 __cb);

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_getResult1 __cb);

    public void end_getResult1(com.nci.tkb.Caller.paramMapHolder rt, Ice.AsyncResult __result);

    public java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm);

    public java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm);

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, Ice.Callback __cb);

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, Callback_CallHandler_getResult2 __cb);

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_getResult2 __cb);

    public java.util.Map<java.lang.String, byte[]> end_getResult2(Ice.AsyncResult __result);

    public java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm);

    public java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm);

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, Ice.Callback __cb);

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, Callback_CallHandler_asynCall __cb);

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_asynCall __cb);

    public java.util.Map<java.lang.String, byte[]> end_asynCall(Ice.AsyncResult __result);

    public boolean asynCall_async(AMI_CallHandler_asynCall __cb, java.util.Map<java.lang.String, byte[]> pm);

    public boolean asynCall_async(AMI_CallHandler_asynCall __cb, java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx);

    public java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm);

    public java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm);

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, Ice.Callback __cb);

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, Callback_CallHandler_asynCallAdd __cb);

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_asynCallAdd __cb);

    public java.util.Map<java.lang.String, java.lang.String> end_asynCallAdd(Ice.AsyncResult __result);

    public boolean asynCallAdd_async(AMI_CallHandler_asynCallAdd __cb, java.util.Map<java.lang.String, java.lang.String> pm);

    public boolean asynCallAdd_async(AMI_CallHandler_asynCallAdd __cb, java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx);

    public void shutdown();

    public void shutdown(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_shutdown();

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_shutdown(Ice.Callback __cb);

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_shutdown(Callback_CallHandler_shutdown __cb);

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Callback_CallHandler_shutdown __cb);

    public void end_shutdown(Ice.AsyncResult __result);
}
