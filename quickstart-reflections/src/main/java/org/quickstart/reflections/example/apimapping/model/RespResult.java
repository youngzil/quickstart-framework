
package org.quickstart.reflections.example.apimapping.model;

import java.util.HashMap;
import java.util.Map;

public class RespResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    public RespResult()
    {
        put( "code", Integer.valueOf( 0 ) );
        put( "msg", "操作成功" );
    }

    public static RespResult error()
    {
        return error( 1, "操作失败" );
    }

    public static RespResult error( String msg )
    {
        return error( 500, msg );
    }

    public static RespResult error( int code, String msg )
    {
        RespResult respResult = new RespResult();
        respResult.put( "code", Integer.valueOf( code ) );
        respResult.put( "msg", msg );
        return respResult;
    }

    public static RespResult ok()
    {
        return new RespResult();
    }

    public static RespResult ok( String msg )
    {
        RespResult respResult = new RespResult();
        respResult.put( "msg", msg );
        return respResult;
    }

    public static RespResult ok( Map<String, Object> map )
    {
        RespResult respResult = new RespResult();
        respResult.putAll( map );
        return respResult;
    }

    @Override
    public RespResult put( String key, Object value )
    {
        super.put( key, value );
        return this;
    }
}
