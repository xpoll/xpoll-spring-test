package cn.blmdz.httpclient;

import lombok.Data;

@Data
public class Item3 {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 问题ID
	 */
	private Integer questionId;
	/**
	 * 问题
	 */
	private String question;
	/**
	 * 答案 除以16
	 */
	private Integer answer;
	/**
	 * 章节ID
	 */
	private Integer chapterId;
	/**
	 * 难度
	 */
	private Integer difficulty;
	/**
	 * 提示
	 */
	private String explain;
	/**
	 * 标签
	 */
	private String label;
	/**
	 * 图片多媒体
	 */
	private String mediaContent;
	private Integer mediaHeight;
	private Integer mediaType;
	private Integer mediaWidth;
	// 选项
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private String optionF;
	private String optionG;
	private String optionH;
	/**
	 * 选项类型
	 * 0 判断
	 * 1 选择
	 */
	private Integer optionType;
	/**
	 * 错误数量
	 */
	private Integer falseCount;
	/**
	 * 正确数量
	 */
	private Integer trueCount;
	/**
	 * 错误率
	 */
	private Double wrongRate;
}
