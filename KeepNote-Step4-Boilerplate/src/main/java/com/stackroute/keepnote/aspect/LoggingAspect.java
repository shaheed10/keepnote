package com.stackroute.keepnote.aspect;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {

  /*
   * Write loggers for each of the methods of controller, any particular method
   * will have all the four aspectJ annotation
   * (@Before, @After, @AfterReturning, @AfterThrowing).
   */
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
   
   
    @Pointcut("execution(* com.stackroute.keepnote.controller.*.*(..))")
    public void allcontrollers() {}
   
    @Before("allcontrollers()")
   public void Before(JoinPoint point)
   {
        logger.info("-------------begin---------");
        logger.debug("Signature is " + point.getSignature().getName());
        logger.debug("Array tostring" + Arrays.toString(point.getArgs()));
        logger.info("-------------end of before controller---------");
   }
   
 
 
    @After("allcontrollers()")
       public void After(JoinPoint point)
       {
            logger.info("------------after method---------");
            logger.debug("Signature is " + point.getSignature().getName());
            logger.debug("Array tostring" + Arrays.toString(point.getArgs()));
            logger.info("-------------after done---------");
       }
   
    @AfterReturning(pointcut="allcontrollers()",returning="ret")
       public void Afterreturning(JoinPoint point,String ret)
       {
            logger.info("------------after return method---------");
            logger.debug("returned obj is " + ret);
            logger.info("-------------after return done---------");
       }
   
    @AfterThrowing(pointcut="allcontrollers()",throwing="ex")
       public void AfterThrowing(JoinPoint point,Exception ex)
       {
            logger.info("------------after throwing method---------");
            logger.debug("returned obj is " + ex.toString());
            logger.info("-------------after throwing done---------");
       }








}