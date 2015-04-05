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

public final class CallHandlerPrxHelper extends Ice.ObjectPrxHelperBase implements CallHandlerPrx
{
    private static final String __asynCall_name = "asynCall";

    public java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm)
    {
        return asynCall(pm, null, false);
    }

    public java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx)
    {
        return asynCall(pm, __ctx, true);
    }

    private java.util.Map<java.lang.String, byte[]> asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "asynCall", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("asynCall");
                    __delBase = __getDelegate(false);
                    _CallHandlerDel __del = (_CallHandlerDel)__delBase;
                    return __del.asynCall(pm, __ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm)
    {
        return begin_asynCall(pm, null, false, null);
    }

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx)
    {
        return begin_asynCall(pm, __ctx, true, null);
    }

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, Ice.Callback __cb)
    {
        return begin_asynCall(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_asynCall(pm, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, Callback_CallHandler_asynCall __cb)
    {
        return begin_asynCall(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_asynCall __cb)
    {
        return begin_asynCall(pm, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_asynCall(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__asynCall_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __asynCall_name, __cb);
        try
        {
            __result.__prepare(__asynCall_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            com.nci.tkb.Caller.paramMapHelper.write(__os, pm);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public java.util.Map<java.lang.String, byte[]> end_asynCall(Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __asynCall_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            java.util.Map<java.lang.String, byte[]> __ret;
            __ret = com.nci.tkb.Caller.paramMapHelper.read(__is);
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    public boolean asynCall_async(AMI_CallHandler_asynCall __cb, java.util.Map<java.lang.String, byte[]> pm)
    {
        Ice.AsyncResult __r;
        try
        {
            __checkTwowayOnly(__asynCall_name);
            __r = begin_asynCall(pm, null, false, __cb);
        }
        catch(Ice.TwowayOnlyException ex)
        {
            __r = new IceInternal.OutgoingAsync(this, __asynCall_name, __cb);
            __r.__exceptionAsync(ex);
        }
        return __r.sentSynchronously();
    }

    public boolean asynCall_async(AMI_CallHandler_asynCall __cb, java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx)
    {
        Ice.AsyncResult __r;
        try
        {
            __checkTwowayOnly(__asynCall_name);
            __r = begin_asynCall(pm, __ctx, true, __cb);
        }
        catch(Ice.TwowayOnlyException ex)
        {
            __r = new IceInternal.OutgoingAsync(this, __asynCall_name, __cb);
            __r.__exceptionAsync(ex);
        }
        return __r.sentSynchronously();
    }

    private static final String __asynCallAdd_name = "asynCallAdd";

    public java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm)
    {
        return asynCallAdd(pm, null, false);
    }

    public java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx)
    {
        return asynCallAdd(pm, __ctx, true);
    }

    private java.util.Map<java.lang.String, java.lang.String> asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "asynCallAdd", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("asynCallAdd");
                    __delBase = __getDelegate(false);
                    _CallHandlerDel __del = (_CallHandlerDel)__delBase;
                    return __del.asynCallAdd(pm, __ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm)
    {
        return begin_asynCallAdd(pm, null, false, null);
    }

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx)
    {
        return begin_asynCallAdd(pm, __ctx, true, null);
    }

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, Ice.Callback __cb)
    {
        return begin_asynCallAdd(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_asynCallAdd(pm, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, Callback_CallHandler_asynCallAdd __cb)
    {
        return begin_asynCallAdd(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_asynCallAdd __cb)
    {
        return begin_asynCallAdd(pm, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_asynCallAdd(java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__asynCallAdd_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __asynCallAdd_name, __cb);
        try
        {
            __result.__prepare(__asynCallAdd_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            com.nci.tkb.Caller.paramMapsHelper.write(__os, pm);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public java.util.Map<java.lang.String, java.lang.String> end_asynCallAdd(Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __asynCallAdd_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            java.util.Map<java.lang.String, java.lang.String> __ret;
            __ret = com.nci.tkb.Caller.paramMapsHelper.read(__is);
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    public boolean asynCallAdd_async(AMI_CallHandler_asynCallAdd __cb, java.util.Map<java.lang.String, java.lang.String> pm)
    {
        Ice.AsyncResult __r;
        try
        {
            __checkTwowayOnly(__asynCallAdd_name);
            __r = begin_asynCallAdd(pm, null, false, __cb);
        }
        catch(Ice.TwowayOnlyException ex)
        {
            __r = new IceInternal.OutgoingAsync(this, __asynCallAdd_name, __cb);
            __r.__exceptionAsync(ex);
        }
        return __r.sentSynchronously();
    }

    public boolean asynCallAdd_async(AMI_CallHandler_asynCallAdd __cb, java.util.Map<java.lang.String, java.lang.String> pm, java.util.Map<String, String> __ctx)
    {
        Ice.AsyncResult __r;
        try
        {
            __checkTwowayOnly(__asynCallAdd_name);
            __r = begin_asynCallAdd(pm, __ctx, true, __cb);
        }
        catch(Ice.TwowayOnlyException ex)
        {
            __r = new IceInternal.OutgoingAsync(this, __asynCallAdd_name, __cb);
            __r.__exceptionAsync(ex);
        }
        return __r.sentSynchronously();
    }

    private static final String __getResult1_name = "getResult1";

    public void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt)
    {
        getResult1(pm, rt, null, false);
    }

    public void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt, java.util.Map<String, String> __ctx)
    {
        getResult1(pm, rt, __ctx, true);
    }

    private void getResult1(java.util.Map<java.lang.String, byte[]> pm, com.nci.tkb.Caller.paramMapHolder rt, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "getResult1", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("getResult1");
                    __delBase = __getDelegate(false);
                    _CallHandlerDel __del = (_CallHandlerDel)__delBase;
                    __del.getResult1(pm, rt, __ctx, __observer);
                    return;
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm)
    {
        return begin_getResult1(pm, null, false, null);
    }

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx)
    {
        return begin_getResult1(pm, __ctx, true, null);
    }

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, Ice.Callback __cb)
    {
        return begin_getResult1(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getResult1(pm, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, Callback_CallHandler_getResult1 __cb)
    {
        return begin_getResult1(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_getResult1 __cb)
    {
        return begin_getResult1(pm, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_getResult1(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getResult1_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __getResult1_name, __cb);
        try
        {
            __result.__prepare(__getResult1_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            com.nci.tkb.Caller.paramMapHelper.write(__os, pm);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public void end_getResult1(com.nci.tkb.Caller.paramMapHolder rt, Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __getResult1_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            rt.value = com.nci.tkb.Caller.paramMapHelper.read(__is);
            __result.__endReadParams();
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    private static final String __getResult2_name = "getResult2";

    public java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm)
    {
        return getResult2(pm, null, false);
    }

    public java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx)
    {
        return getResult2(pm, __ctx, true);
    }

    private java.util.Map<java.lang.String, byte[]> getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "getResult2", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("getResult2");
                    __delBase = __getDelegate(false);
                    _CallHandlerDel __del = (_CallHandlerDel)__delBase;
                    return __del.getResult2(pm, __ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm)
    {
        return begin_getResult2(pm, null, false, null);
    }

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx)
    {
        return begin_getResult2(pm, __ctx, true, null);
    }

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, Ice.Callback __cb)
    {
        return begin_getResult2(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getResult2(pm, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, Callback_CallHandler_getResult2 __cb)
    {
        return begin_getResult2(pm, null, false, __cb);
    }

    public Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, Callback_CallHandler_getResult2 __cb)
    {
        return begin_getResult2(pm, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_getResult2(java.util.Map<java.lang.String, byte[]> pm, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getResult2_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __getResult2_name, __cb);
        try
        {
            __result.__prepare(__getResult2_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            com.nci.tkb.Caller.paramMapHelper.write(__os, pm);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public java.util.Map<java.lang.String, byte[]> end_getResult2(Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __getResult2_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            java.util.Map<java.lang.String, byte[]> __ret;
            __ret = com.nci.tkb.Caller.paramMapHelper.read(__is);
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    private static final String __shutdown_name = "shutdown";

    public void shutdown()
    {
        shutdown(null, false);
    }

    public void shutdown(java.util.Map<String, String> __ctx)
    {
        shutdown(__ctx, true);
    }

    private void shutdown(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "shutdown", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __delBase = __getDelegate(false);
                    _CallHandlerDel __del = (_CallHandlerDel)__delBase;
                    __del.shutdown(__ctx, __observer);
                    return;
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_shutdown()
    {
        return begin_shutdown(null, false, null);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx)
    {
        return begin_shutdown(__ctx, true, null);
    }

    public Ice.AsyncResult begin_shutdown(Ice.Callback __cb)
    {
        return begin_shutdown(null, false, __cb);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_shutdown(__ctx, true, __cb);
    }

    public Ice.AsyncResult begin_shutdown(Callback_CallHandler_shutdown __cb)
    {
        return begin_shutdown(null, false, __cb);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Callback_CallHandler_shutdown __cb)
    {
        return begin_shutdown(__ctx, true, __cb);
    }

    private Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __shutdown_name, __cb);
        try
        {
            __result.__prepare(__shutdown_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            __result.__writeEmptyParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public void end_shutdown(Ice.AsyncResult __result)
    {
        __end(__result, __shutdown_name);
    }

    public static CallHandlerPrx checkedCast(Ice.ObjectPrx __obj)
    {
        CallHandlerPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof CallHandlerPrx)
            {
                __d = (CallHandlerPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    CallHandlerPrxHelper __h = new CallHandlerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static CallHandlerPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        CallHandlerPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof CallHandlerPrx)
            {
                __d = (CallHandlerPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    CallHandlerPrxHelper __h = new CallHandlerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static CallHandlerPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        CallHandlerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    CallHandlerPrxHelper __h = new CallHandlerPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static CallHandlerPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        CallHandlerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    CallHandlerPrxHelper __h = new CallHandlerPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static CallHandlerPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        CallHandlerPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof CallHandlerPrx)
            {
                __d = (CallHandlerPrx)__obj;
            }
            else
            {
                CallHandlerPrxHelper __h = new CallHandlerPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static CallHandlerPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        CallHandlerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            CallHandlerPrxHelper __h = new CallHandlerPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::Caller::handler::CallHandler",
        "::Ice::Object"
    };

    public static String ice_staticId()
    {
        return __ids[0];
    }

    protected Ice._ObjectDelM __createDelegateM()
    {
        return new _CallHandlerDelM();
    }

    protected Ice._ObjectDelD __createDelegateD()
    {
        return new _CallHandlerDelD();
    }

    public static void __write(IceInternal.BasicStream __os, CallHandlerPrx v)
    {
        __os.writeProxy(v);
    }

    public static CallHandlerPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            CallHandlerPrxHelper result = new CallHandlerPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}