package com.mock.mocker;

import com.mock.config.MockConfig;
import com.mock.Mocker;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

@SuppressWarnings("unchecked")
public class BaseMocker<T> implements Mocker<T> {

    private Type type;

    private Type[] genericTypes;

    public BaseMocker(Type type, Type... genericTypes) {
        this.type = type;
        this.genericTypes = genericTypes;
    }

    /**
     * 所有已知子接口：GenericArrayType, ParameterizedType, TypeVariable, WildcardType
     * <p>
     *      1.ParameterizedType： 表示一种参数化的类型，比如 Collection，即普通的泛型。
     *      2.TypeVariable：是各种类型变量的公共父接口，就是泛型里面的类似T、E。
     *      3.GenericArrayType：表示一种元素类型是参数化类型或者类型变量的数组类型，比如List<>[]，T[]这种。
     *      4.WildcardType：代表一种通配符类型表达式，类似? super T这样的通配符表达式。
     *
     * @param mockConfig 模拟数据配置
     * @return
     */
    @Override
    public T mock(MockConfig mockConfig) {
        Mocker mocker;
        if (type instanceof ParameterizedType) {
            mocker = new GenericMocker((ParameterizedType) type);
        } else if (type instanceof GenericArrayType) {
            mocker = new ArrayMocker(type);
        } else if (type instanceof TypeVariable) {
            mocker = new BaseMocker(mockConfig.getVariableType(((TypeVariable) type).getName()));
        } else {
            mocker = new ClassMocker((Class) type, genericTypes);
        }
        return (T) mocker.mock(mockConfig);
    }

}
