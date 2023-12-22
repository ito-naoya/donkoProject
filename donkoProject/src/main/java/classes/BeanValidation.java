package classes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import jakarta.servlet.http.HttpServletRequest;

public class BeanValidation {
	
	public static <T, I> Boolean validate (HttpServletRequest request, String key, T bean, Class<I> groupClass ) {
		
		// Validator を取得
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // バリデーションを実行
        Set<ConstraintViolation<T>> result = validator.validate(bean, groupClass);
        
        //バリデーションメッセージを保持するmapをnew
        Map<String, String> validationMap = new LinkedHashMap<String, String>();
        
         //beanのフィールドをキー、エラーメッセージをバリューとしてMapに追加
        for(ConstraintViolation<T> rs : result){
        validationMap.put(rs.getPropertyPath().toString(), rs.getMessage());
        }
        
        //mapの長さが０より大きい場合（validateのアノテーションでいずれかが引っかかった場合）
        if(validationMap.size() > 0) {
        	
        	//beanのフィールドをキー、エラーメッセージをバリューとしてsetAttributeする
        	for(Map.Entry<String, String> msg : validationMap.entrySet()) {
        		request.setAttribute(msg.getKey(), msg.getValue());
        	}
        	
        	//入力された値を保持したbeanを任意のキーでsetAttribute
        	request.setAttribute(key, bean);

        	//入力内容にエラーあり
            return true;
        }	
        
        //入力内容にエラーなし
        return false;
        
	}
}
