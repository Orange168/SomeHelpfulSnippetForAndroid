// get Annotation methds from android.view.ViewDebug.java
private static HashMap<AccessibleObject, ExportedProperty> sAnnotations;
  methods = klass.getDeclaredMethods();
  int count = methods.length;
        for (int i = 0; i < count; i++) {
            final Method method = methods[i];
            if (method.getParameterTypes().length == 0 &&
                    method.isAnnotationPresent(ExportedProperty.class) &&
                    method.getReturnType() != Void.class) {
                method.setAccessible(true);
                foundMethods.add(method);
                sAnnotations.put(method, method.getAnnotation(ExportedProperty.class));
            }
        } 
 final Method[] methods = getExportedPropertyMethods(klass);
 final Class<?> returnType = method.getReturnType();
 final Class<?> type = field.getType();
                final ExportedProperty property = sAnnotations.get(field);
                String categoryPrefix =/*get the annotation value*/
                        property.category().length() != 0 ? property.category() + ":" : "";
    if (returnType == int.class) {
      ……
    }else if(returnType == int[].class) {
      ……
    }
    
 private static Object callMethodOnAppropriateTheadBlocking(final Method method,
            final Object object) throws IllegalAccessException, InvocationTargetException,
            TimeoutException {
        if (!(object instanceof View)) {
            return method.invoke(object, (Object[]) null);
        }

        final View view = (View) object;
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws IllegalAccessException, InvocationTargetException {
                return method.invoke(view, (Object[]) null);
            }
        };
        FutureTask<Object> future = new FutureTask<Object>(callable);
        // Try to use the handler provided by the view
        Handler handler = view.getHandler();
        // Fall back on using the main thread
        if (handler == null) {
            handler = new Handler(android.os.Looper.getMainLooper());
        }
        handler.post(future);
        while (true) {
            try {
                return future.get(CAPTURE_TIMEOUT, java.util.concurrent.TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                Throwable t = e.getCause();
                if (t instanceof IllegalAccessException) {
                    throw (IllegalAccessException)t;
                }
                if (t instanceof InvocationTargetException) {
                    throw (InvocationTargetException)t;
                }
                throw new RuntimeException("Unexpected exception", t);
            } catch (InterruptedException e) {
                // Call get again
            } catch (CancellationException e) {
                throw new RuntimeException("Unexpected cancellation exception", e);
            }
        }
    }  
    
    public static Object invokeViewMethod(final View view, final Method method,
            final Object[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Object> result = new AtomicReference<Object>();
        final AtomicReference<Throwable> exception = new AtomicReference<Throwable>();

        view.post(new Runnable() {
            @Override
            public void run() {
                try {
                    result.set(method.invoke(view, args));
                } catch (InvocationTargetException e) {
                    exception.set(e.getCause());
                } catch (Exception e) {
                    exception.set(e);
                }

                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (exception.get() != null) {
            throw new RuntimeException(exception.get());
        }

        return result.get();
    }
    
    public static void setLayoutParameter(final View view, final String param, final int value)
            throws NoSuchFieldException, IllegalAccessException {
        final ViewGroup.LayoutParams p = view.getLayoutParams();
        //Todo core coding 1
        final Field f = p.getClass().getField(param);
        if (f.getType() != int.class) {
            throw new RuntimeException("Only integer layout parameters can be set. Field "
                    + param + " is of type " + f.getType().getSimpleName());
        }
        //Todo core coding 2
        f.set(p, Integer.valueOf(value));

        view.post(new Runnable() {
            @Override
            public void run() {
                view.setLayoutParams(p);
            }
        });
    }
