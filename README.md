# project-enotetaker
# Team:
Scott/Chi/Cole/Fayazud

# Trello:
https://trello.com/b/HZFBDy31/scribe-squart-for-note-taker (url)


## Project Abstract
For this project, we will build an application that took the picture of your hand writting and automatically output the .txt/.doc file that you can work with on computer, personally I think it is a super functional application since you can really put it in use in your exam/review study. 

For the picture recognition part, We will build a Neural Network (NN) which is trained on word-images from the IAM dataset. As the input layer (and therefore also all the other layers) can be kept small for word-images, NN-training is feasible on the CPU (of course, a GPU would be better). This implementation is the bare minimum that is needed for the application using Tensorflow.

For the neural network coding part, using Python and finish in Ananconda/Tensorflow would be the quickest way since we can put the training part on Amazon AWS (surely I can do this part). 

## Project Relevance
The broader topics covered in this course introduces the students to the following topics and to demonstrate their practical application. 
-  Object Oriented Design;

-  Test Driven Development; 

-  Unified Modeling Language (UML); 

-  Design Patterns;

-  Debugging–Code ;

-  profiling and optimization–On-the-fly coding;

-  Graphic User Interface .




## Conceptual Design
>This is the basic conceptual design of the recognition system.

![Picture1](https://user-images.githubusercontent.com/54897894/66152820-4b4cab80-e5e8-11e9-9537-9d55f08b34c1.png)

CNN(convolutional neural network): the input image is fed into the CNN layers. These layers are trained to extract relevant features from the image. Each layer consists of three operation. First, the convolution operation, which applies a filter kernel of size 5×5 in the first two layers and 3×3 in the last three layers to the input. Then, the non-linear RELU function is applied. Finally, a pooling layer summarizes image regions and outputs a downsized version of the input. 

RNN(recurrent neural networks): the feature sequence contains 256 features per time-step, the RNN propagates relevant information through this sequence. The popular Long Short-Term Memory (LSTM) implementation of RNNs is used, as it is able to propagate information through longer distances and provides more robust training-characteristics than vanilla RNN. The RNN output sequence is mapped to a matrix of size 32×80. 

Data Input: it is a gray-value image of size 128×32. Usually, the images from the dataset do not have exactly this size, therefore we resize it (without distortion) until it either has a width of 128 or a height of 32. Then, we copy the image into a (white) target image of size 128×32. Finally, we normalize the gray-values of the image which simplifies the task for the NN. 

For the similar project, please see:
[https://github.com/jeremyjordan/dog-breed-classifier](url)
Sample project that use RNN to create hand written notes:
https://www.cs.toronto.edu/~graves/handwriting.cgi?text=&style=&bias=0.15&samples=3 (url)
Online sample app that looks really close to Enote, except it takes the hand written letters online and generate text at the same time
https://ai.googleblog.com/2019/03/rnn-based-handwriting-recognition-in.html (url)

# SAMPLE CODE(WEEK 1)
## Sample code slightly simplified implementation of Kim's [Convolutional Neural Networks for Sentence Classification](http://arxiv.org/abs/1408.5882) paper in Tensorflow and will give us a headstart of the project

## Requirements

- Python 3
- Tensorflow > 0.12
- Numpy

## Training

Print parameters:

```bash
./train.py --help
```

```
optional arguments:
  -h, --help            show this help message and exit
  --embedding_dim EMBEDDING_DIM
                        Dimensionality of character embedding (default: 128)
  --filter_sizes FILTER_SIZES
                        Comma-separated filter sizes (default: '3,4,5')
  --num_filters NUM_FILTERS
                        Number of filters per filter size (default: 128)
  --l2_reg_lambda L2_REG_LAMBDA
                        L2 regularizaion lambda (default: 0.0)
  --dropout_keep_prob DROPOUT_KEEP_PROB
                        Dropout keep probability (default: 0.5)
  --batch_size BATCH_SIZE
                        Batch Size (default: 64)
  --num_epochs NUM_EPOCHS
                        Number of training epochs (default: 100)
  --evaluate_every EVALUATE_EVERY
                        Evaluate model on dev set after this many steps
                        (default: 100)
  --checkpoint_every CHECKPOINT_EVERY
                        Save model after this many steps (default: 100)
  --allow_soft_placement ALLOW_SOFT_PLACEMENT
                        Allow device soft device placement
  --noallow_soft_placement
  --log_device_placement LOG_DEVICE_PLACEMENT
                        Log placement of ops on devices
  --nolog_device_placement

```

Train:

```bash
./train.py
```

## Evaluating

```bash
./eval.py --eval_train --checkpoint_dir="./runs/1459637919/checkpoints/"
```

Replace the checkpoint dir with the output from the training. To use your own data, change the `eval.py` script to load your data.


## References

- [Convolutional Neural Networks for Sentence Classification](http://arxiv.org/abs/1408.5882)
- [A Sensitivity Analysis of (and Practitioners' Guide to) Convolutional Neural Networks for Sentence Classification](http://arxiv.org/abs/1510.03820)
