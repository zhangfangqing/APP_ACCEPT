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

package com.nci.tkb.Caller.exceptions;

public class NoSuchResult extends SpanError
{
    public NoSuchResult()
    {
        super();
    }

    public NoSuchResult(Throwable __cause)
    {
        super(__cause);
    }

    public NoSuchResult(String reason)
    {
        super(reason);
    }

    public NoSuchResult(String reason, Throwable __cause)
    {
        super(reason, __cause);
    }

    public String
    ice_name()
    {
        return "Caller::exceptions::NoSuchResult";
    }

    protected void
    __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice("::Caller::exceptions::NoSuchResult", -1, false);
        __os.endWriteSlice();
        super.__writeImpl(__os);
    }

    protected void
    __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
        super.__readImpl(__is);
    }

    public static final long serialVersionUID = 1641351731L;
}
