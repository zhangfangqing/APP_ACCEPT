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

public abstract class Callback_CallHandler_getResult2 extends Ice.TwowayCallback
{
    public abstract void response(java.util.Map<java.lang.String, byte[]> __ret);

    public final void __completed(Ice.AsyncResult __result)
    {
        CallHandlerPrx __proxy = (CallHandlerPrx)__result.getProxy();
        java.util.Map<java.lang.String, byte[]> __ret = null;
        try
        {
            __ret = __proxy.end_getResult2(__result);
        }
        catch(Ice.LocalException __ex)
        {
            exception(__ex);
            return;
        }
        response(__ret);
    }
}
